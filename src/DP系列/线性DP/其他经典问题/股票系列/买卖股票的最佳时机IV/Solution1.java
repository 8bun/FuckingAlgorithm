package DP系列.线性DP.其他经典问题.股票系列.买卖股票的最佳时机IV;

/**
 * 如果不采用框架的解法，这是比较经典和复杂的动态规划问题，因为需要同时记录两个状态，对状态的定义也比较严格，值得好好分析
 * 我们在每一天都把这 k 次交易都模拟一遍，记录这一天的 k 次交易的状态，并在后续不停的更新
 * (更通俗的讲就是)
 * 状态定义：dp[i][j] 表示第 i 次交易, 行为是「买 | 卖」的状态，其中 j = 0 表示买；j = 1 表示卖
 * 状态转移： 其中 price 表示当天的价格
 * 第 i 次交易的买入： dp[i][0] = dp[i - 1][1] - price
 * 第 i 次交易的卖出： dp[i][1] = dp[i][0] + price
 * 初始化： dp[i][0] = -∞(方便比较)
 * 注意的点：
 * 第一次交易比较特殊，单独计算一下 dp[0][0] 和 dp[0][1],后面 k-1 次都是基于第一次交易
 * 当交易数 k > length /2 的时候，k 就没有意义了，因为天数限制了交易次数必须小于 length/2，
 * 所以这种情况也就相当于无限次交易
 *
 * @author cwq
 * @since 2021/03/02
 */
public class Solution1 {

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n < 2 || k < 1) return 0;
        if (k >= n / 2) {
            return getMax(prices);
        }
        // dp[i][j] 表示第 i 次交易, 行为是「买 | 卖」的状态，其中 j = 0 表示买；j = 1 表示卖
        int[][] dp = new int[k][2];
        for (int i = 0; i < k; i++) {
            dp[i][0] = Integer.MIN_VALUE;
        }
        // 遍历每一天，模拟 k 次交易，计算并更新状态
        for (int p : prices) {
            // 第 1 次买
            dp[0][0] = Math.max(dp[0][0], - p);
            // 第 1 次卖
            dp[0][1] = Math.max(dp[0][1], dp[0][0] + p);
            for (int i = 1; i < k; i++) {
                // 第 i 次买
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] - p);
                // 第 i 次卖
                dp[i][1] = Math.max(dp[i][1], dp[i][0] + p);
            }
        }
        return dp[k - 1][1];
    }

    /**
     * 当k>=prices.len/2时，结果都是一样的
     *
     * @param prices
     *
     * @return
     */
    public int getMax(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += (prices[i] - prices[i - 1]);
            }
        }
        return res;
    }
}
