package DP系列.线性DP.其他经典问题.两个子序列的最大点积;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/24 19:37
 * @Description:
 * @Url: https://leetcode-cn.com/problems/max-dot-product-of-two-subsequences/
 * @限制:
 * @Level:
 */
public class Solution {
    /**
     * 设dp[i][j]表示nums1的[0...i]即前i+1个数字与nums2的[0...j]前j+1个数字所能做到的最大子序列的点积和
     * 那么nums1[i]和nums2[j]可以选择加入最大子序列也可以选择不加入：dp[i][j] = dp[i - 1][j - 1] + nums1[i] * nums2[j]
     * 也可以选择一个加入一个不加入：
     * 那么dp[i][j] = dp[i - 1][j];
     * 或dp[i][j] = dp[i][j-1];
     * 都不加入则就是上面两步的作用
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                dp[i][j] =  nums1[i] * nums2[j];
            }
        }
        int ans = nums1[0] * nums2[0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= 1) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                if(j >= 1) dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                if (i >= 1 && j >= 1) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + nums1[i] * nums2[j]);
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
