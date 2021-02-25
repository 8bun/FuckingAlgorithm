package DFS系列.扫雷游戏;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/2 11:17
 * @Description:
 * @Url: https://leetcode-cn.com/problems/minesweeper/
 * 题目理解：https://www.bilibili.com/video/av67491968?from=search&seid=14179055963851621748
 * @限制:
 * @Level:
 */
public class Solution {

    private int[][] dirs={{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        dfs(board,click);
        return board;
    }
    private void dfs(char[][] board,int[] click){
        int startRow=click[0],startCol=click[1];
        if (board[startRow][startCol]=='M'){ //地雷（'M'）被挖出，游戏就结束了-把它改为 'X'
            board[startRow][startCol]='X';return;
        }
        //board[startRow][startCol]='E'
        int row=board.length,col=board[0].length;
        int num=0;
        //计算8个方向的总雷数
        for (int[] dir:dirs){
            int newRow=startRow+dir[0];
            int newCol=startCol+dir[1];
            if (newRow<0||newCol<0||newRow>=row||newCol>=col)continue;
            if (board[newRow][newCol]=='M')
                num++;
        }
        //如果8个方向都没有雷，那么自身改为B，然后对8个方向进行深搜
        if (num==0){
            board[startRow][startCol]='B';
            for (int[] dir:dirs){
                int newRow=startRow+dir[0];
                int newCol=startCol+dir[1];
                if (newRow<0||newCol<0||newRow>=row||newCol>=col)continue;
                if (board[newRow][newCol]!='B') //防止重复dfs
                     dfs(board,new int[]{newRow,newCol});
            }
        }
        else //如果8个方向中有雷，那么自身改为雷的数量，不进行深搜
            board[startRow][startCol]=(char)(num+'0');
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board={{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}};
//        char[][] board={{'E','E','M'},{'E','E','E'},{'E','E','E'}};
        int[] click={2,0};
        for (char[] row:board){
            for (char ch:row)
                System.out.print(ch+" ");
            System.out.println();
        }
        System.out.println();
        char[][] res=new Solution().updateBoard(board,click);
        for (char[] row:res){
           for (char ch:row)
               System.out.print(ch+" ");
            System.out.println();
        }
    }
}
