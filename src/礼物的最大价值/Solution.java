package 礼物的最大价值;

/**
 * @description：动态规划解决
 * 假设dp[i][j]表示从a[0][0]出发经过右下运动到达a[i][j]处的最大价值，那么
 * 问题是求dp[i][j]的最大值，分解为子问题，由于a[i][j]是通过上一个运动点通过右或下运动到达的，
 * 那么逆推回去，上一个运动点到a[i][j]的值的和也肯定选最大的，a[i][j]向上为a[i-1][j]，向左为
 * a[i][j-1]，那么dp[i][j]=max(dp[i−1][j],dp[i][j−1])+a[i][j]，也就是说，这样就将从a[0][0]出发经过右下运动到达a[i][j]处的最大价值
 * 转化为了从a[0][0]出发经过右下运动到达a[i-1][j]或者a[i][j-1]处的最大价值（因为只有a[i-1][j]或者a[i][j-1]能够到达a[i][j]）
 * 将式子进行i=0,j=0赋值，得到三个初始化值：
 * dp[0][0] = a[0][0]
 * dp[i][0] = dp[i-1][0] + a[i][0]
 * dp[0][j] = dp[0][j-1] + a[0][j]
 * @url：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/
 * @限制：
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 * @author：Jack
 * @createTime：2020/3/4 13:54
 * @level：中等
 */
public class Solution {
    public int maxValue(int[][] grid) {
        int row=grid.length;
        int col=grid[0].length;

        int[][] dp=new int[row][col];
        //初始化dp数组的部分值
        dp[0][0]=grid[0][0];
//        dp[0][0]已初始化，从1开始
        for(int i=1;i<row;i++)
            dp[i][0]=dp[i-1][0] + grid[i][0];  //初始化dp数组第一行
        for(int j=1;j<col;j++)
            dp[0][j] = dp[0][j-1] + grid[0][j];  //初始化dp数组第一列
        //dp数组第一行和第一列已初始化，从(1,1)开始
        for(int i=1;i<row;i++)
            for (int j=1;j<col;j++)
                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1])+grid[i][j];
        return dp[row-1][col-1];
    }
}
