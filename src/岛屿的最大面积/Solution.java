package 岛屿的最大面积;

/**
 * @description： 每个岛屿之间是相互隔离的，那么每一次的dfs岛屿修改值不会影响另一个元素开始的dfs
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/11 16:29
 * @level：
 */
public class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int res=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                if(grid[i][j]==1) //岛屿都是1组成的，0的没意义
                    res=Math.max(dfs(i,j,grid),res);
            }
        }
        return res;
    }
    //每个点又进行四个方向的搜寻
    private int dfs(int i, int j, int[][] grid) {
        if(i<0||j<0||i>=grid.length||j>=grid[i].length||grid[i][j]==0)
            return 0;  //这个点为源（的四个方向直到满足if条件）找到1的数目
        grid[i][j]=0; //修改值，防止在同一个源内来回兜圈，另一个源是不可能访问到这里的，因为岛屿之间是分隔开来的
        return 1+dfs(i+1,j,grid)+dfs(i-1,j,grid)+dfs(i,j+1,grid)+dfs(i,j-1,grid);
    }

    public static void main(String[] args) {
        int[][] grid=
                {{1,1,0,0,0},
                 {1,1,0,0,0},
                 {0,0,0,1,1},
                 {0,0,0,1,1}};
       int res=new Solution().maxAreaOfIsland(grid);
        System.out.println(res);
    }
}
