package DP系列.背包DP.零钱兑换;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/13 09:58
 * @Description: 完全背包
 * @Url: https://leetcode-cn.com/problems/coin-change/
 * @限制:
 * @Level:
 */
public class Solution {

    public int coinChange(int[] coins, int amount) {

        if (coins.length == 0 )return -1;
        int[] dp = new int[amount + 1]; //dp[i]:构成i个硬币所需要的最少硬币数
        Arrays.fill(dp, Integer.MAX_VALUE);  //因为要比较的是最小值，这个不可能的值就得赋值成为一个最大值
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++){
            for (int w = coins[i]; w <= amount; w++) { //从能容纳coins[i]的背包开始遍历
                if (dp[w-coins[i]] != Integer.MAX_VALUE) //要求放入coins[i]后剩余容量的最小硬币已经求出才能有效
                    dp[w] = Math.min(dp[w], dp[w - coins[i]] + 1);  //不放入coins[i]和放入coins[i]
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
