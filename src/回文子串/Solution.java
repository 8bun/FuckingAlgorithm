package 回文子串;

import java.util.Scanner;

/**
 * @description：
 * dp[i][j]表示从i到j是否是回文串，当然i<=j时才有意义, i>j时无意义
 * 当i==j时，表示一个字符，为true
 * i!=j时，dp[i][j]表示从i到j是否是回文串==
 * s[i]==s[j] && 从i+1到j-1是否是回文串dp[i+1][j-1]|
 * s[i]==s[j] && j-i<2，[i,j]字符串长度为1或2
 * 总结：
 * 当只有一个字符时，比如a自然是一个回文串。
 * 当有两个字符时，如果是相等的，比如aa，也是一个回文串。
 * 当有三个及以上字符时，比如ababa这个字符记作串1，把两边的a去掉，也就是bab记作串2，可以看出只要串2是一个回文串，
 * 那么左右各多了一个a的串1必定也是回文串。所以当s[i]==s[j]时，自然要看dp[i+1][j-1]是不是一个回文串。
 * @url：https://leetcode-cn.com/problems/palindromic-substrings/
 * @限制：
 * @author：Jack
 * @createTime：2020/3/26 21:14
 * @level：
 */
class Solution {
    public int countSubstrings(String s) {
        // 动态规划法
        boolean[][] dp = new boolean[s.length()][s.length()];
        int ans = 0;

        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ans++;
                }
            }
        }

        return ans;
    }
}

