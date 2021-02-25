package DP系列.线性DP.其他经典问题.股票系列.买卖股票的最佳时机III;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/22 21:01
 * @Description:
 * @Url: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 * @限制: 你最多可以完成 两笔 交易。k<=2
 * @Level:
 */
public class Solution {
    //两个数组first以及second，first[i]存储price[0]-price[i]进行一次交易的最大利润，
    //second[i]存储price[i]到price[len-1]的最大利润，两个数组再相加得到的数组最大值就是两次交易的最大利润。
    public int maxProfit(int[] prices) {
        int len=prices.length;
        if (len==0)
            return 0;
        int[] firstMaxProfix=new int[len];
        int[] secondMaxProfix=new int[len];
        firstMaxProfix[0]=0;
        for (int i=1,minPrice=prices[0],maxProfix=0;i<len;i++){
            minPrice=Math.min(minPrice,prices[i]);
            maxProfix=Math.max(maxProfix,prices[i]-minPrice);
            firstMaxProfix[i]=maxProfix;
        }
        secondMaxProfix[len-1]=0;
        //这里要注意，逆序的时候是要求前面遍历的最大值，也就是最大的卖出价格
        for (int i=len-2,maxPrice=prices[len-1],maxProfit=0;i>=0;i--){
            maxPrice=Math.max(maxPrice,prices[i]);
            maxProfit=Math.max(maxProfit,maxPrice-prices[i]);
            secondMaxProfix[i]=maxProfit;
        }
        int res=0;
        for (int i=0;i<len;i++)
            res=Math.max(res,firstMaxProfix[i]+secondMaxProfix[i]);
        return res;
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
 * k<=2时，
 * 这里我们保留k的状态，因为等式右边还是会存在k-1的状态的，所以仍然保留（强行解释，其实是不太清楚）
 */
    public int maxProfit1(int[] prices) {
        int max_k = 2;
        int n=prices.length;
        if(n==0)
            return 0;
        int[][][] dp = new int[n][max_k + 1][2];
        //穷举了 n × max_k × 2 个状态
        for (int i = 0; i < n; i++) {
            for (int k = 1; k <=max_k ; k--) { //这里正序和逆序都可以
                if (i - 1 == -1) {
                    dp[0][k][0] =0;
                    dp[0][k][1] =-prices[0];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
            }
        }
        return dp[n - 1][max_k][0];
    }

    /**
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--29/
     * 动规做法：
     * 用 dp[i][k] 表示prices[0]...prices[i]这几天最多交易k次的最高收益
     * dp[i][k] = Max(dp[i-1][k],prices[i] - prices[j] + dp[j][k-1])，j=[0,i]
     * 而 prices[i] - prices[j] + dp[j][k-1] 也可以看做， prices[i] - (prices[j] - dp[j][k-1]) ，
     * 为了求这个表达式的最大值，我们可以找prices[j] - dp[j][k-1]的最小值
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int len=prices.length;
        if (len==0)
            return 0;
        int k=2;
        int[][] dp=new int[len][k+1];
        for (int time=1;time<=k;time++){
            int min=prices[0];
            for (int j=1;j<len;j++){
                min=Math.min(prices[j]-dp[j][time-1],min);
                dp[j][time]=Math.max(dp[j-1][time],prices[j]-min);
            }
        }
        return dp[len-1][k];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] prices={3,3,5,0,0,3,1,4};
        int res=new Solution().maxProfit1(prices);
        System.out.println(res);
    }
}
