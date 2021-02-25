package 并查集系列.朋友圈;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/8 10:18
 * @Description:
 * @Url: https://leetcode-cn.com/problems/friend-circles/
 * @限制:
 * @Level:
 */
public class Solution {

    private int[] parent;
    private int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};
    private int count; //连通分量个数
    public int findCircleNum(int[][] M) {
        if (M==null||M.length==0)return 0;
        int row = M.length, col = M[0].length;
        parent=new int[row];
        count=row;
        for (int i=0; i<row; i++)
            parent[i]=i;
        //连接i,j,连通量-1
        //好友关系是双向关系，因此题目中给出的矩阵其实是一个邻接矩阵，所以问题就转化为求图中有几个连通分量的问题了。
        //由于是对阵矩阵，我们其实只要看这个矩阵的下三角形部分就可以了
        //又因为自己肯定是自己的好友，所以还可以不看对角。
        for (int i=0; i<row; i++) {
            for (int j = 0; j < i ; j++) {
                if (M[i][j] == 1) {
                    union(i,j);
                }
            }
        }
        return count;
    }
    private int find(int x){
        while (x!=parent[x]){
            parent[x]=parent[parent[x]];
            x=parent[x];
        }
        return x;
    }
    private void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)return;
        parent[rootP] = rootQ;

        count--;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
