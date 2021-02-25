package DP系列.线性DP.其他经典问题.股票系列.买卖股票的最佳时机;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/22 20:31
 * @Description:
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 遍历数组，边维护一个最小买入价格，维护一个当前最大收益
 * @Url:
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * @限制: 你不能在买入股票前卖出股票。k=1
 * @Level:
 */
public class Solution {

    public int maxProfit(int[] prices) {
        if (prices.length==0)
            return 0;
        int minPrice=prices[0];
        int maxProfit=0; //申请了2个内存，空间复杂度O(1)
        for (int price:prices){
            maxProfit=Math.max(price-minPrice,maxProfit);  //price-minPrice为minPrice买入然后当天卖出时的收益
            minPrice=Math.min(minPrice,price);
        }
        return maxProfit;
    }

    /**
     * dp思想：dp[i]表示prices[0]...price[i]区间的最大收益
     * 像上面类似，还要维护一个minPrice
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int len=prices.length;
        if (len==0)
            return 0;
        int[] dp=new int[len];  //申请了len个内存，空间复杂度O(n)
        dp[0]=0;
        int minPrice=prices[0];
        for (int i=1;i<len;i++) {
            dp[i] = Math.max(prices[i] - minPrice, dp[i - 1]);
            minPrice=Math.min(prices[i],minPrice);
        }
        return dp[len-1];
    }

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
     * 化简：
     * 当k==1时
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
     *             = max(dp[i-1][1][1], -prices[i])
     * 解释：k = 0 的 初始状态，所以 dp[i-1][0][0] = 0。
     * 现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了。
     * 可以进行进一步化简去掉所有 k：
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], -prices[i])
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int len=prices.length;
        if(len==0)
            return 0;
        int[][] dp=new int[len][2];
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        for (int i=1;i<len;i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }
        return dp[len-1][0];
    }

    /**
     * 在上面的做法中，新状态只和相邻的一个状态有关，其实不用整个 dp 数组，
     * 只需要一个变量储存相邻的那个状态就足够了，这样可以把空间复杂度降到 O(1):
     * 空间复杂度O(1)的做法
     * @param prices
     */
    public int maxProfit3(int[] prices) {
        int len=prices.length;
        if(len==0)
            return 0;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
