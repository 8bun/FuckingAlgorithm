package DP系列.线性DP.其他经典问题.打家劫舍;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/10 09:34
 * @Description:
 * @Url: https://leetcode-cn.com/problems/house-robber/
 * @限制:
 * @Level:
 */

public class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        if (len == 1)
            return nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], dp[0]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len-1];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
