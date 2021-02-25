package DFS系列.飞地的数量;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/2 13:54
 * @Description:
 * @Url: https://leetcode-cn.com/problems/number-of-enclaves/
 * @限制:
 * @Level:
 */
public class Solution {
    private int[][] dirs={{0,-1},{0,1},{1,0},{-1,0}};
    public int numEnclaves(int[][] A) {
        int maxRow=A.length-1;
        int maxCol=A[0].length-1;
        int res=0;
        for (int i=0;i<=maxRow;i++){
            for (int j=0;j<=maxCol;j++){
                if (A[i][j]==1){
                    if (isOnEdge(i,j,maxRow,maxCol,A)){
                        dfs(i,j,A,maxRow,maxCol);
                    }
                }
            }
        }
        for (int i=0;i<=maxRow;i++) {
            for (int j = 0; j <= maxCol; j++) {
                if (A[i][j]==1)
                    res++;
            }
        }
        return res;
    }

    /**
     * 1、找出从上下左右边界为1的值，如果有1，从当前x、y坐标开始，向四周辐射递归置为0。（病毒式蔓延）
     * 2、最后求出没有被病毒感染到的个数，也就1的数量
     * @param i
     * @param j
     * @param A
     * @param maxRow
     * @param maxCol
     */
    private void dfs(int i,int j,int[][] A,int maxRow,int maxCol){
        A[i][j]=0;
        for (int[] dir:dirs){
            int newX=i+dir[0];
            int newY=j+dir[1];
            if (newX<0||newY<0||newX>maxRow||newY>maxCol)continue;
            if (A[newX][newY]==1)
                dfs(newX,newY,A,maxRow,maxCol);
        }
    }
    private boolean isOnEdge(int row,int col,int maxRow,int maxCol,int[][] A){
        return row==0||row==maxRow||col==0||col==maxCol;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int[][] A={{0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}};
        int[][] A={{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
        for (int[] row:A){
            for (int num:row)
                System.out.print(num+" ");
            System.out.println();
        }
        int res=new Solution().numEnclaves(A);
        System.out.println(res);
    }
}
