package 完全平方数;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/17 21:27
 * @Description: 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 其实跟硬币找0是一样的，n是总金额，然后从1,4,9,..m^2寻找最少的选择来组成n（m^2<=n），归结为01背包
 * @Url: https://leetcode-cn.com/problems/perfect-squares
 * @限制:
 * @Level:
 */
public class Solution {

    public int numSquares(int n) {
        int[] dp = new int[n + 1]; //dp[i]表示背包容量i的情况下，使用1,4,9,..m^2寻找最少的选择来组成i（m^2<=i）的最少个数
        Arrays.fill(dp, 1, n + 1, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                //例如dp[12]=min{dp[11]+1,dp[8]+1,dp[3]+1}
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);  //dp[i]可能会有多种选择,因为j是变化的
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
