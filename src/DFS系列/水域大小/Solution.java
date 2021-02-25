package DFS系列.水域大小;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/7 13:29
 * @Description:
 * DFS求解所遍历的0的总个数
 * @Url: https://leetcode-cn.com/problems/pond-sizes-lcci/
 * @限制:
 * @Level:
 */
public class Solution {
    private List<Integer> res=new ArrayList<>();
    private int[][] dirs={{0,1},{0,-1},{1,0},{-1,0},{-1,1},{-1,-1},{1,-1},{1,1}};
    private boolean[][] vis;
    public int[] pondSizes(int[][] land) {
        int row=land.length,col=land[0].length;
        vis=new boolean[row][col];
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if (land[i][j]==0) {
                    if (!vis[i][j]) {
                        res.add(dfs(i,j,land,row,col));
                    }
                }
            }
        }
        return res.stream().mapToInt(Integer::valueOf).sorted().toArray();
    }

    /**
     * 从(x,y)出发，8个方向的0的总个数（包括(x,y)一个）
     * 假如(newX,newY)为它的一个值为0的邻居
     * 那么就包括从(newX,newY)出发，8个方向的0的总个数（包括(newX,newX)一个）
     * 按照这种包含的递归进行求和
     * 每个合法的邻居又会进行递归
     * @param x
     * @param y
     * @param land
     * @param row
     * @param col
     * @return
     */
    private int dfs(int x, int y, int[][] land, int row, int col) {
        if (isValid(x,y,land,row,col)) //如果越界或者非0，返回0
            return 0;
        int sum=1;  //合法x,y，数量为1，sum最多为8
        vis[x][y]=true;
        for (int[] dir:dirs){
            int newX=x+dir[0],newY=y+dir[1]; //非法的返回0
            sum+=dfs(newX, newY, land, row, col); //合法的1个(x,y)加上(x,y)8个方向的数量
        }
        return sum;
    }

    private boolean isValid(int x,int y,int[][] land,int row,int col){
        return x<0||y<0||x>=row||y>=col||land[x][y]!=0||vis[x][y];
    }
    public static void main(String[] args) {
        int[][] land={
                {0,0,1,0},
                {0,0,0,1},
                {1,0,0,1},
                {0,1,0,1}};
        int[] res=new Solution().pondSizes(land);
        for (int i:res){
            System.out.print(i+" ");
        }
    }
}
