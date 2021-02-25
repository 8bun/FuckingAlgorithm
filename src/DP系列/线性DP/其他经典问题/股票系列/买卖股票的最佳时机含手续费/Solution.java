package DP系列.线性DP.其他经典问题.股票系列.买卖股票的最佳时机含手续费;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/23 18:27
 * @Description:
 * @Url: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * @限制: 可以无限次地完成交易，但是你每笔交易都需要付手续费
 *
 * @Level:
 */
public class Solution {
    /**
     * 框架写法：
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/yi-ge-tong-yong-fang-fa-tuan-mie-6-dao-gu-piao-wen/
     * dp[i][k][0 or 1]
     * ① 含义解释：我们可以用自然语言描述出每一个状态的含义，
     *      比如说 dp[3][2][1] 的含义就是：今天是第三天，我现在手上持有着股票，至今最多进行 2 次交易。
     *      再比如 dp[2][3][0] 的含义：今天是第二天，我现在手上没有持有股票，至今最多进行 3 次交易。
     * ② 结果表示：我们想求的最终答案是 dp[n - 1][K][0]，
     *      即最后一天，最多允许 K 次交易，最多获得多少利润。为什么不是 dp[n - 1][K][1]？
     *      因为 [1] 代表手上还持有股票，[0] 表示手上的股票已经卖出去了，很显然后者得到的利润一定大于前者。
     * ----------------------------------------------------------------------------
     * 转态转移方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *               max(   选择 rest  ,           选择 sell      )
     * 解释：今天我没有持有股票，有两种可能：
     * 要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
     * 要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
     *
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *               max(   选择 rest  ,           选择 buy         )
     * 解释：今天我持有着股票，有两种可能：
     * 要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
     * 要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
     * ----------------------------------------------------------------------------
     * 初始状态：
     * dp[-1][k][0] = 0
     * 解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
     *
     * dp[-1][k][1] = -infinity
     * 解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
     *
     * dp[i][0][0] = 0
     * 解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
     *
     * dp[i][0][1] = -infinity
     * 解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
     * ---------------------------------------------------------------------------
     * 总结：
     * 初始状态：
     * dp[-1][k][0] = dp[i][0][0] = 0
     * dp[-1][k][1] = dp[i][0][1] = -infinity
     *
     * 状态转移方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * ---------------------------------------------------------------------------
     * 当k=正无穷时，分析跟II一样，只是，在买入或卖出股票时的总利润再减去手续费即可
     * 这里的fee在前面的式子减去也是可以的
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i]-fee)
     * 同样的，这里我们使用O(1)时间复杂度的做法
     **/
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        if (n==0)
            return 0;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]-fee);
        }
        return dp_i_0;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
