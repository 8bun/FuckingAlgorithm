package DFS系列.被围绕的区域;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/7 19:50
 * @Description:
 * DFS 修改从边界的O能遍历到的所有O为P
 * 最后将还是O的变成X，将P变成O
 * @Url: https://leetcode-cn.com/problems/surrounded-regions/
 * @限制:
 * @Level:
 */
public class Solution {
    private int[][] dirs={{0,1},{0,-1},{1,0},{-1,0}};
    public void solve(char[][] board) {
        int row = board.length;
        if (row==0)return;
        int col = board[0].length;

        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                if (board[i][j]=='O'&&isOnEdge(i, j, row, col))
                    dfs(i,j,row,col,board);
            }
        }
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                if (board[i][j]=='O')board[i][j]='X';
                else if (board[i][j]=='P')board[i][j]='O';
            }
        }
    }
    private boolean isOnEdge(int x, int y, int row, int col){
        return x==0||y==0||x==row-1||y==col-1;
    }

    private void dfs(int x, int y, int row, int col, char[][] board){
        if (board[x][y]=='X')return;
        board[x][y]='P';
        for (int[] dir:dirs){
            int newX=dir[0]+x, newY=dir[1]+y;
            if (newX<0||newY<0||newX>=row||newY>=col)continue;
            if (board[newX][newY]=='O')
                dfs(newX,newY,row,col,board);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
