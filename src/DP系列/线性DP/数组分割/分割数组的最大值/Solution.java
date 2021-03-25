package DP系列.线性DP.数组分割.分割数组的最大值;

import java.util.Arrays;

/**
 * https://www.cnblogs.com/grandyang/p/5933787.html
 * @author cwq
 * @link https://leetcode-cn.com/problems/split-array-largest-sum/
 * @since 2021/03/23
 */
public class Solution {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = nums[i] + prefix[i];
        }
        dp[0][0] = 0;
        // 前i个数分成j组
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(m, i); j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], prefix[i] - prefix[k]));
                }
            }
        }
        return dp[n][m];
    }
}
