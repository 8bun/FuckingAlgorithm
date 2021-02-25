package BFS系列.连通网络的操作次数;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/5/4 17:00
 * @Description:
 * 关于连通性的题目均可以使用并查集来解决
 * @Url: https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected/
 * @限制:
 * @Level:
 */
public class Solution {
    public int makeConnected(int n, int[][] connections) {
        if (connections.length<n-1) //至少需要n-1条网线才能连通所有计算机
            return -1;
        HashMap<Integer, HashSet<Integer>> map=new HashMap<>();//set存储所有邻居
        for (int[] conn:connections){
            //这里注意双向存
            HashSet<Integer> neighbors1=map.getOrDefault(conn[0],new HashSet<>());
            HashSet<Integer> neighbors2=map.getOrDefault(conn[1],new HashSet<>());
            neighbors1.add(conn[1]);
            map.put(conn[0],neighbors1);
            neighbors2.add(conn[0]);
            map.put(conn[1],neighbors2);
        }
        Deque<Integer> deque=new ArrayDeque<>();
        //遍历所有计算机的状态，每个连通分量看成一座岛，所以最后需要岛的数量-1个连接线来进行岛的连接
        //保持相同岛的计算机都可以同时出现在队列的相邻两个空状态之间
        int nums=0;
        boolean[] vis=new boolean[n];
        for (int i=0;i<n;i++){

            if (vis[i])continue;
            deque.add(i);
            nums++; //连通分量的数量
            while (!deque.isEmpty()){
                Integer start=deque.poll();
                vis[start]=true;
                if (map.get(start)==null||map.get(start).size()==0)continue;
                System.out.println("start"+start+":nei"+map.get(start));
                for (Integer neis:map.get(start)){
                    if (vis[neis])continue;
                    deque.add(neis);
                    vis[neis]=true;  //0:{1,3,7},3:{2,5,7}，那么将1,3,7分别加入队列后，就不需要再去遍历1,3,7的所有的
                    //直接相邻结点了，因为0能到3，那么也就是说0能到{2,5,7}，所以在i=2,5,7以及1、7的所有邻居的时候就不需要进入while循环了
                }
            }
        }
        return nums-1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
