package DP系列.线性DP.骰子问题.n个骰子的点数;

/**
 * @description：
 * n个骰子的出现和为sum的概率=n个骰子的和为sum的总排列情况/n个骰子和的所有情况的总的排列情况为6^n
    dp[i][j]代表i枚色子和为j的概率，由于最后一个有6种可能的点，所以
    递推公式很容易想到 当j>6时，dp[i][j]= 1/6(dp[i-1][j-1]+dp[i-1][j-2]+dp[i-1][j-3]...dp[i-1][j-6])
        j<6时，dp[i][j]= 1/6(dp[i-1][j-1]+dp[i-1][j-2]+dp[i-1][j-3]...dp[i-1][j-j])
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/5 10:55
 * @level：
 */
public class Solution {
    public double[] twoSum(int n) {
        double dp[][]=new double[n+1][6*n+1];
        double probablity=1.0/6.0;
        for(int i=1;i<=6;i++){ //一枚骰子
            dp[1][i]=probablity;
        }
        for (int num=2;num<=n;num++){ //num=[2,n]枚骰子
            for (int sum=num;sum<=6*num;sum++){ //i枚骰子的点数和的范围[num,6*num]
                //由于dp[num][sum]= 1/6(dp[num-1][sum-1]+dp[num-1][sum-2]+dp[num-1][sum-3]...dp[num-1][sum-6])
                for(int k=1;sum>=k /*防止sum-k为负数*/ && k<=6;k++){
                    dp[num][sum]+=probablity*(dp[num-1][sum-k]);

                }
            }
        }
        double[] res=new double[5*n+1];
        System.arraycopy(dp[n],n, res,0,res.length); //dp[n]表示n枚骰子的情况
        return res;
    }
}
