package DP系列.区间DP.最长回文子串;


/**
 * @author cwq
 * @since 2021/03/14
 * @link https://leetcode-cn.com/problems/longest-palindromic-substring/submissions/
 */
public class Solution {

    public String longestPalindrome(String A) {
        int n = A.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        // 字符串首尾下标差
        // 在状态转移方程中，我们是从长度较短的字符串向长度较长的字符串进行转移的
        // 所以从长度小的开始
        for(int l = 0; l < n; l++) {
            // 字符串起始下标
            for(int i = 0; i + l < n; i++) {
                // 字符串结束下标
                int j = i + l;
                if(l == 0) {
                    // i==j
                    dp[i][j] = true;
                }
                else if(l == 1) {
                    // j == i+1
                    dp[i][j] = (A.charAt(i) == A.charAt(j));
                }
                else {
                    dp[i][j] = (A.charAt(i) == A.charAt(j)) && dp[i + 1][j - 1];
                }
                if(dp[i][j] && l + 1 > ans.length()) {
                    ans = A.substring(i, j + 1);
                }
            }
        }
        return ans;
    }
}
