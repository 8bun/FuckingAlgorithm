package 背包问题系列.应用.硬币找零总方案数;

/**
 * @description： 递归实现，基于动态规划分析
 * @url： https://leetcode-cn.com/problems/coin-lcci/
 * @限制： 0 <= n (总金额) <= 1000000
 * 当n=50000，会超时
 * @author：Jack
 * @createTime：2020/4/5 17:43
 * @level：
 */
public class Solution2 {
    public int waysToChange(int n) {

        int[] coin={1,5,10,25};
        return recursiveChargeTypes(coin,coin.length-1,n);
    }
    //递归实现,i,j含义跟dp[i][j]一样
    public int recursiveChargeTypes(int[] coinsValues, int i, int j) {
        //基准条件 可以 通过画一个简单的实例 分析来得出. 比如 recursiveChargeTypes({1,3,4}, 3, 5)
        if(j == 0)  //coin[0]到coin[i]几种硬币组成0元，一种情况
            return 1;
        if(j < 0)
            return 0;
        if(i < 0)
            return 0;
//        if(i==0)  //第一种硬币组成j元（看题目的coin情况）
//            return 1;
        else
            return (recursiveChargeTypes(coinsValues, i-1, j)%1000000007 + recursiveChargeTypes(coinsValues, i, j-coinsValues[i])%1000000007)%1000000007;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().waysToChange(10));
    }
}
