package 并查集系列.被围绕的区域;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/7 20:16
 * @Description:
 * 除了DFS做法外，还有并查集做法
 * 让处于边界的O以及能到的的结点的祖先为一个虚拟的结点，而位于被包围的内部O结点的祖先不为这个虚拟结点
 * @Url: https://leetcode-cn.com/problems/surrounded-regions/submissions/
 * 相似做法的题目：岛屿的数量:https://www.bilibili.com/video/BV1b7411E7yq?t=1322
 * https://www.bilibili.com/video/BV1P7411w7ZV?t=257
 * @限制:
 * @Level:
 */
public class Solution {

    private int[] parent;
    private int[][] dirs={{0,1},{0,-1},{1,0},{-1,0}};
    public void solve(char[][] board) {
        if (board == null || board.length == 0)
            return;

        int row = board.length;
        int col = board[0].length;

        parent=new int[row * col + 2];
        //用一个虚拟节点, 边界上的O的祖先节点都是这个虚拟节点
        parent[row * col + 1] = row * col + 1;
        //初始化
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O')
                    parent[i * col + j] = i * col + j; //自身,二维数组每个坐标唯一性的表示
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O'){
                    if (isOnEdge(i, j, row, col))
                        union(i * col + j, row * col + 1);
                    else {//与上下左右的O合并祖先
                        for (int[] dir : dirs){
                            int newX = dir[0] + i;
                            int newY = dir[1] + j;
                            if (newX < 0 || newY < 0 || newX >= row || newY >= col || board[newX][newY] == 'X')continue;
                            union(i * col + j, newX * col + newY);
                        }
                    }
                }
            }
        }
        //找出孤岛并改变，孤岛的祖先不等于 row * col + 1;
        //而所有边界的祖先都等于 row * col + 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O' && find(i * col + j) != row * col + 1)
                    board[i][j] = 'X';
            }
        }
    }
    private boolean isOnEdge(int i, int j, int row, int col){
        return  i==0||j==0||i==row-1||j==col-1;
    }
    /**
     * 寻找x的祖先
     * @param x
     */
    private int find(int x){
        while (x!=parent[x]){
            parent[x]=parent[parent[x]]; //路径压缩
            x=parent[x];
        }
        return x;
//        if (parent[x]!=x){
//            parent[x]=find(parent[x]);
//        }
//        return parent[x];
    }

    /**
     * 连接p和q
     * @param p
     * @param q
     */
    private void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;
        //这里使小指向大，使得同一分量的元素有规律地指向较大的祖先
        if (rootP < rootQ)  parent[rootP] = rootQ;
        else parent[rootQ] = rootP;
        //这里可以为每个分量维护一个size，让size小的指向size大的，较平衡
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
