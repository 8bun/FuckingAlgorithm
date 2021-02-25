package T秒后青蛙的位置;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/5/23 23:07
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution {

    private double eps = 1e-5;
    boolean flag = false;
    ArrayList<Integer> path = new ArrayList<>();

    private HashMap<Integer, List<Integer>> edgesMap = new HashMap<>();
    public double frogPosition(int n, int[][] edges, int t, int target) {
        if (edges.length==0) {
            return 1.0;
        }
        //先按一维排序，得到1的位置
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        boolean flag = true; //是否正向建图
        if (edges[0][0] != 1)
            flag = false;

        for (int[] edge : edges){
            if (flag) {  //[1,*]
                List<Integer> list = edgesMap.getOrDefault(edge[0], new ArrayList<>());
                list.add(edge[1]);
                edgesMap.put(edge[0], list);
            }else { //[*,1]
                List<Integer> list = edgesMap.getOrDefault(edge[1], new ArrayList<>());
                list.add(edge[0]);
                edgesMap.put(edge[1], list);
            }
        }
        path.add(1);
        backtrace(n, edges, t, target, 1, path);
//        System.out.println(path.toString()); //得到的path为[1,..target,target]
        //时间不够或者到不了target或者t时间内只能经过target但是永远到不了target例如1->2->3,target=2,t=7，那么第1s到target，其余6s都到3
        if (path.size() - 2 > t || path.get(path.size() - 1) != target || (t - path.size() + 2 != 0)&&edgesMap.get(target) != null) return 0.0;
        double res = 1.0;
        for (int i = 0; i < path.size() - 1; i++){
            if (edgesMap.get(path.get(i)) == null) continue;
            //概率之积
            res *= 1.0 / (double) edgesMap.get(path.get(i)).size();
        }
        return res;
    }
    private void backtrace(int n, int[][] edges, int t, int target, int curNode,List<Integer> path){
        if (curNode == target){
            path.add(curNode);
            flag = true; //是否已经找到path
            return;
        }
        if (edgesMap.get(curNode) == null) return;

        if (!flag) {
            for (int x : edgesMap.get(curNode)) {
                path.add(x);
                backtrace(n, edges, t, target, x, path);
                if (flag)
                    return;
                path.remove((Integer) x);
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 7;
        int[][] edges = {{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}};
        int t = 2;
        int target = 4;
        double res = new Solution().frogPosition(n, edges, t, target);
        System.out.println(res);
    }
}
