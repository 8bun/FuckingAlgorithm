package DP系列.背包DP.零钱兑换II;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/13 11:08
 * @Description:
 * @Url: https://leetcode-cn.com/problems/coin-change-2/
 * @限制:
 * @Level:
 */
public class Solution {

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1]; //构成总金额i的方案数
        dp[0] = 1;
        for (int coin : coins){
            for (int w = coin; w <= amount; w++)
                dp[w] += dp[w-coin];
        }
        return dp[amount];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
