package BFS系列.朋友圈;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/5 17:53
 * @Description:
 * 这道题目也是关于连通性的，可以使用并查集来解决
 * 与连通网络的操作次数这道题相同，都是求连通分量个数
 * 这里的M[i][j]=1就表示连通网络的操作次数中的i和j相连(i!=j)
 * 同样的，也可以使用BFS来解决
 * 这里在i相同的情况下，对M[i][j]中为1的都是看成i的邻居
 * 如：M
 * 1 1 0 1
 * 1 1 1 0
 * 0 1 1 0
 * 1 1 1 1
 * 那么
 * 0的邻居为1,3
 * 1的邻居为1,2
 * 2的邻居为1
 * 3的邻居为0,1,2
 * @Url: https://leetcode-cn.com/problems/friend-circles/solution/peng-you-quan-by-leetcode/
 * @限制:
 * 矩阵范围 N*N
 * N 在[1,200]的范围内。
 * 对于所有学生，有M[i][i] = 1。
 * 如果有M[i][j] = 1，则有M[j][i] = 1。
 * @Level:
 */
public class Solution {

    private boolean[] vis;
    public int findCircleNum(int[][] M) {
        vis=new boolean[M.length];
        Deque<Integer> deque=new ArrayDeque<>();
        int num=0;
        for (int i=0;i<M.length;i++){
            if (vis[i])continue;
            deque.addLast(i);
            num++;
            while (!deque.isEmpty()){
                int start=deque.pollFirst();
                vis[start]=true;
                int size = deque.size();
                //遍历所有朋友（不包括自己）
                for (int friend=0;friend<M.length;friend++){
                    if (friend==start||vis[friend]||M[start][friend]==0)continue;
                    deque.addLast(friend);
                    vis[friend]=true;
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
