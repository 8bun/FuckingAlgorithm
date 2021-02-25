package DFS系列.收集树上所有苹果的最少时间;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/10 11:39
 * @Description:
 *
 * @Url: https://leetcode-cn.com/contest/weekly-contest-188/problems/minimum-time-to-collect-all-apples-in-a-tree/
 * @限制:
 * @Level:
 */

public class Solution {
    private HashMap<Integer,List<Integer>> map=new HashMap<>();

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        buildMap(n,edges,hasApple); //建立所有邻居关系
        return bfs(0,hasApple);
    }
    private void buildMap(int n, int[][] edges, List<Boolean> hasApple){
        for (int[] edge:edges){
            List<Integer> list;
            if (map.get(edge[0])==null)
                list=new ArrayList<>();
            else
                list=map.get(edge[0]);
            list.add(edge[1]);
            map.put(edge[0],list);
        }
    }

    /**
     * bfs实现所有结点的标记以及计算总路径
     * @param cur
     * @param hasApple
     */
    private int bfs(int cur,List<Boolean> hasApple){
        Deque<Integer> deque=new ArrayDeque<>();
        deque.addLast(0);
        int cnt=0;
        while (!deque.isEmpty()){

            int size=deque.size();

            for (int i=0;i<size;i++){

                int root=deque.pollFirst();
                
                List<Integer> sons=map.get(root);
                if (sons!=null){
                    for (int son:sons) {
                        //如果root的后代中（可能是儿子的儿子...）有苹果，那么从根（不包括）到该后代（包括）之间的路径长度*2为总时间长度
                        //这里用每次+2来计算乘2的结果，因为bfs保证了从根（不包括）到该后代（不包括）之间的结点都能被访问
                        if (dfs(son,hasApple)){
                            cnt += 2;
                        }
                        deque.addLast(son);
                    }
                }
            }
        }
        return cnt;
    }


    /**
     * dfs判断从根节点cur出发的路径有没有苹果
     * 找到一个苹果即返回true
     */
    private boolean dfs(int cur,List<Boolean> hasApple){
        if (hasApple.get(cur))return true;
        if (map.get(cur)==null)return false;
        for (int son:map.get(cur)){
            if (dfs(son,hasApple)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n=6;
        int[][] edges={{0,1},{0,2},{1,3},{3,4},{4,5}};
        List<Boolean> hasApple=new ArrayList<>();
        boolean[] h={false,true,false,false,true,true};
        for (Boolean has:h){
            hasApple.add(has);
        }
        int res=new Solution().minTime(n,edges,hasApple);
        System.out.println(res);
    }
}
