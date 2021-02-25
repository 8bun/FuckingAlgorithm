package DFS系列.通知所有员工所需的时间;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/5/23 21:57
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution {
    private HashMap<Integer, List<Integer>> map = new HashMap<>();
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        for(int i = 0; i < n; i++){
            List<Integer> sons = map.getOrDefault(manager[i], new ArrayList<>());
            sons.add(i);
            map.put(manager[i], sons); //管理员,儿子
        }
//        System.out.println(map);
        if (map.get(headID) == null || map.get(headID).size() == 0){
            return 0;
        }
        int res = 0;
        for (Integer son : map.get(headID)){
            res = Math.max(res, dfs(son, informTime, informTime[headID]));
        }
        return res;
    }
    private int dfs( int curManager, int[] informTime, int cnt){

        if (map.get(curManager) == null || map.get(curManager).size() == 0)
            return cnt;
        int res = 0;
        List<Integer> sons = map.get(curManager);
        for (int i = 0; i < sons.size(); i++) {
            res = Math.max(res, dfs(sons.get(i), informTime, cnt + informTime[curManager]));
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 15, headID = 0, manager[] = {-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6}, informTime[] = {1,1,1,1,1,1,1,0,0,0,0,0,0,0,0};
        //int n = 1, headID = 0, manager[] = {-1}, informTime[] = {0};
        int res = new Solution().numOfMinutes(n, headID, manager, informTime);
        System.out.println(res);
    }
}
