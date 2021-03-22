package DP系列.递推型DP.爬楼梯;

import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/6/11 10:19
 * @Description:
 * @link https://leetcode-cn.com/problems/climbing-stairs/
 * @限制:
 * @Level:
 */
public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
