package DP系列.线性DP.其他经典问题.股票系列.买卖股票的最佳时机含手续费;

/**
 * 定义二维数组 dp[n][2]：
 * dp[i][0] 表示第 i 天不持有可获得的最大利润；
 * dp[i][1] 表示第 i 天持有可获得的最大利润（注意是第 i 天持有，而不一定是第 i 天买入）
 * 详解先看买卖股票的最佳时机含冷冻期.Solution1
 *
 * @author cwq
 * @since 2021/03/02
 */
public class Solution1 {

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][1] = - prices[0];
        // 遍历每一天的收益情况
        for (int i = 1; i < n; i++) {
            // 第i天不持有(卖出时才是一次交易完成，需要手续费)
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }
}
