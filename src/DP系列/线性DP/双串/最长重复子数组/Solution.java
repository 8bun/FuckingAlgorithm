package DP系列.线性DP.双串.最长重复子数组; /**
 * @Author: Jack
 * @Date: 2020/7/1 11:27
 * @Description: dp[i][j] 代表 A[0:i - 1] 与 B[0:j - 1] 两个连续数组中公共的、长度最长的子数组的长度；
 * 当A[i-1] == B[j-1]时，dp[i][j] = dp[i-1][j-1] + 1，表示以它们结尾的公共子数组长度为取决于以前面结尾的
 * 公共子数组长度+1;
 * 当A[i-1] != B[j-1]时，"以A[i-1]和B[j-1]结尾"的公共子数组长度为0，dp[i][j] = 0，表示以它们结尾的公共子数组
 * 长度为0。
 * 注意理解“以A[i-1]和B[j-1]结尾”
 * @Url: https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
 * @限制: 
 * @Level: 
 */
public class Solution {
    public int findLength(int[] A, int[] B) {
        int len1 = A.length;
        int len2 = B.length;
        int[][] dp = new int[len1+1][len2+1];
        int res = 0;
        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                dp[i][j] = (A[i-1] == B[j-1]) ? dp[i-1][j-1] + 1 : 0;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
