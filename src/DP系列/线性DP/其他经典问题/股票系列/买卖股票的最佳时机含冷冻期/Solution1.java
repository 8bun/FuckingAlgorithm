package DP系列.线性DP.其他经典问题.股票系列.买卖股票的最佳时机含冷冻期;

/**
 * 不采用框架解法
 * 详情见官方解释
 *
 * @author cwq
 * @since 2021/03/02
 */
public class Solution1 {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 0) {
            return 0;
        }
        //由于可以无限次交易，所以只定义两个维度，
        //第一个维度是天数，第二个维度表示持有股票的状态，0表示持有，1表示处于冷冻期，2表示不持有（但不处于冷冻期）
        //注意：这里的「处于冷冻期」指的是在第 i 天结束之后的状态。也就是说：如果第 i 天结束之后处于冷冻期，那么第 i+1 天无法买入股票
        //其值dp[i]表示第i天结束之后的累计最大收益
        int[][] dp = new int[len][3];
        // 第1次买入持有
        dp[0][0] = - prices[0];
        for (int i = 1; i < len; i++) {
            //第i天结束之后持有股票有两种情况：
            //a.第i-1天也持有股票，第i天不操作（不产生任何收益），
            //b.第i-1天不持有股票，在第i天买入
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            //第i天结束之后是冷冻期只有一种情况，第i-1天买入持有股票，第i天卖出
            dp[i][1] = dp[i - 1][0] + prices[i];
            //第i天不持有股票的情况有两种
            //a.第i-1天结束之后是冷冻期
            //b.第i-1天也不持有股票
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[len - 1][1], dp[len - 1][2]);
    }
}
