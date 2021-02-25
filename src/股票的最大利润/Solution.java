package 股票的最大利润;

/**
 * @description：
 * [7, 1, 5, 3, 6, 4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * @url： https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/
 * @限制： 0 <= 数组长度 <= 10^5
 * @author：Jack
 * @createTime：2020/3/27 10:34
 * @level：
 */
public class Solution {
    public int maxProfit(int[] prices) {
        int len=prices.length;
        if (len==0)
            return 0;
        int minIn=prices[0];
        int maxPf=0;  //最大的收益
        for (int i=0;i<len;i++){
            if (prices[i]<=minIn){
                minIn=prices[i]; //找到第一个最小价格买入
            }
            else {
                maxPf=prices[i]-minIn>maxPf?prices[i]-minIn:maxPf;  //更新最大收益
            }
        }
        return maxPf;
    }
}
