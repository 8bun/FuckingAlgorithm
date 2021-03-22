package DP系列.线性DP.其他经典问题.股票系列.买卖股票的最佳时机含冷冻期;

import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/4/23 17:33
 * @Description:
 * @link https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * @限制: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * @Level:
 */
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }

    /**
     * 框架写法：
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/yi-ge-tong-yong-fang-fa-tuan-mie-6-dao-gu-piao-wen/
     * dp[i][k][0 or 1]
     * ① 含义解释：我们可以用自然语言描述出每一个状态的含义，
     * 比如说 dp[3][2][1] 的含义就是：今天是第三天，我现在手上持有着股票，至今最多进行 2 次交易。
     * 再比如 dp[2][3][0] 的含义：今天是第二天，我现在手上没有持有股票，至今最多进行 3 次交易。
     * ② 结果表示：我们想求的最终答案是 dp[n - 1][K][0]，
     * 即最后一天，最多允许 K 次交易，最多获得多少利润。为什么不是 dp[n - 1][K][1]？
     * 因为 [1] 代表手上还持有股票，[0] 表示手上的股票已经卖出去了，很显然后者得到的利润一定大于前者。
     * ----------------------------------------------------------------------------
     * 转态转移方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * max(   选择 rest  ,           选择 sell      )
     * 解释：今天我没有持有股票，有两种可能：
     * 要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
     * 要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
     * <p>
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * max(   选择 rest  ,           选择 buy         )
     * 解释：今天我持有着股票，有两种可能：
     * 要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
     * 要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
     * ----------------------------------------------------------------------------
     * 初始状态：
     * dp[-1][k][0] = 0
     * 解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
     * <p>
     * dp[-1][k][1] = -infinity
     * 解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
     * <p>
     * dp[i][0][0] = 0
     * 解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
     * <p>
     * dp[i][0][1] = -infinity
     * 解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
     * ---------------------------------------------------------------------------
     * 总结：
     * 初始状态：
     * dp[-1][k][0] = dp[i][0][0] = 0
     * dp[-1][k][1] = dp[i][0][1] = -infinity
     * <p>
     * 状态转移方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * ---------------------------------------------------------------------------
     * 根据前面的解释：
     * * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * *               max(   选择 rest  ,           选择 buy         )
     * * 解释：今天我持有着股票，有两种可能：
     * * dp[i-1][k][1]要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
     * * dp[i-1][k-1][0] - prices[i]表示要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
     * 但是这里的股票只能隔一天进行交易，也就是买的那天的昨天必须是没有股票的状态，并且昨天的前天必须持有股票然后当天卖出，也就是不持有股票的状态
     * 所以，这道题中，今天我持有着股票，有两种可能：
     * 也就是要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
     * 要么买的那天的昨天必须是没有股票的状态，并且昨天的前天必须持有股票然后当天卖出，也就是不持有股票的状态，然后我今天选择buy
     * 所以状态数组为：
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * = max(dp[i-1][k][1], dp[i-2][k][0] - prices[i])
     * 所以等式左右两边都是k的状态，所以可以对其进行压缩
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0)
            return 0;
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        int dp_i_0_pre = 0;
        for (int i = 0; i < n; ++ i) {
            int temp = dp_i_0;/*更新之前的前一个状态的dp_i_0*/
            dp_i_0/*当前最新状态的dp_i_0*/ = Math.max(dp_i_0/*上一个状态的dp_i_0*/, dp_i_1/*上一个状态的dp_i_1*/ + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_i_0_pre - prices[i]);
            dp_i_0_pre = temp; /*保留更新之前的前一个状态的dp_i_0，在进入下一次循环时，先再次更新dp_i_0，为i的状态，然后执行下一个语句更新dp_i_1，
            此时的dp_i_0_pre已经是 [_] [新dp_i_1] [新dp_i_1] 为i-2的状态的dp_i_1了*/
        }
        return dp_i_0;
    }
}
