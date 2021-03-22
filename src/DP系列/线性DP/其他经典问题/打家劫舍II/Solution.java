package DP系列.线性DP.其他经典问题.打家劫舍II;

import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/5/10 10:05
 * @Description: 要求第0家和第n-1家永远也不能同时被偷，所以考虑第0家的时候不要包含第n-1家，考虑第n-1家的时候不要包含第0家
 * 用这两个线性表分别去做198题的动态规划，取大的即可
 * @link https://leetcode-cn.com/problems/house-robber-ii/
 * @限制: 此题中的房间是环状排列的（即首尾相接）
 * @Level:
 */
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = {1, 3, 1, 3, 100};
        int res = new Solution().rob(nums);
        System.out.println(res);
    }

    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        int[] dp = new int[len];
        //0 - len-2
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        int res = dp[len - 2];
        //1 - len-1
        dp[0] = 0;
        dp[1] = nums[1];
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return Math.max(res, dp[len - 1]);
    }
}
