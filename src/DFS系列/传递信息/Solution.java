package DFS系列.传递信息;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/18 22:01
 * @Description: DFS解决
 * @Url: https://leetcode-cn.com/problems/chuan-di-xin-xi/
 * @限制:
 * @Level:
 */
public class Solution {

    private int ans=0;

    public int numWays(int n, int[][] relation, int k) {
        for(int i = 0; i < relation.length; i++){
            if(relation[i][0] == 0) //从0号开始
                dfs(relation, relation[i][1], 1, k, n); //传给0号能直接传递的人，当前轮数1
        }
        return ans;
    }

    /**
     * @param relation
     * @param start 从哪个编号开始传递
     * @param step 当前是第几轮
     * @param k
     * @param n
     */
    void dfs(int[][] relation, int start, int step, int k, int n){
        if(step == k){ //结束传递的条件
            if(start == n-1)
                ans++;
            return;
        }
        for(int i = 0; i < relation.length; i++){ //遍历所有情况，找到start能直接传递的人进行传递
            if(relation[i][0] == start)
                dfs(relation, relation[i][1], step+1, k, n);
        }
    }
    public static void main(String[] args) {
        int[][] re={{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}};
        int k=3;
        int n=5;
        int a=new Solution().numWays(5,re,k);
        System.out.println(a);
        Scanner scanner = new Scanner(System.in);
    }
}
