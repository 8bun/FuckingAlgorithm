package 并查集系列.岛屿数量;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/8 09:45
 * @Description:
 * 并查集做法
 * @Url: https://leetcode-cn.com/problems/number-of-islands/
 * @限制:
 * @Level:
 */
public class Solution {
    private int[] parent;
    private int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};
    private int count=0; //连通分量个数
    public int numIslands(char[][] grid) {
        if (grid==null||grid.length==0)return 0;
        int row = grid.length, col = grid[0].length;
        parent=new int[row*(col+1)];
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                if (grid[i][j]=='1') {
                    parent[i * col + j] = i * col + j;
                    count++;
                }
            }
        }
        for (int i=0; i<row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j]=='1'){
                    //其实两个方向也可以
                    for (int[] dir : dirs){
                        int newX=dir[0]+i;
                        int newY=dir[1]+j;
                        if (newX<0||newY<0||newX>=row||newY>=col||grid[newX][newY]=='0')continue;
                        union(newX*col+newY,i*col+j);
                    }
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
