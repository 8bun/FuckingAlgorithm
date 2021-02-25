package DP系列.区间DP.最长回文子序列;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/11 09:30
 * @Description:
 * f[i][j] 表示 s 的第 i 个字符到第 j 个字符组成的子串中，最长的回文序列长度是多少。
 * 转移方程
 * 如果 s 的第 i 个字符和第 j 个字符相同的话
 * f[i][j] = f[i + 1][j - 1] + 2
 * 如果 s 的第 i 个字符和第 j 个字符不同的话
 * f[i][j] = max(f[i + 1][j], f[i][j - 1])
 * 然后注意遍历顺序，i 从最后一个字符开始往前遍历，j 从 i + 1 开始往后遍历，这样可以保证每个子问题都已经算好了。
 * 初始化
 * f[i][i] = 1 单个字符的最长回文序列是 1
 * 结果
 * f[0][n - 1]
 * @Url: https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 * @限制: 回文串可不连续
 * @Level:
 */
public class Solution {

    public int longestPalindromeSubseq(String s) {
        int len=s.length();
        int[][] dp=new int[len][len]; //dp[i][j]:表示s[i...j]之间的最长子序列的长度
        dp[len-1][len-1]=1;
        //因为回文子序列是对称的，倒过来不变.所以求一个序列的最长回文子序列，就是求该序列与其逆序列的最长公共子序列
        /**
         * ①当s[i]==s[j]时，说明i与j位置的字符可以形成一个回文，这个回文的长度为2,根据dp的思想，其结果应该是依赖前面的结果,也就是
         * s[i+1 .... j-1]这个范围的字符回文个数，也就是dp[i+1][j-1]，即dp[i][j]=dp[i+1][j-1]+2
         * ②当s[i]!=s[j]时，说明i与j位置的字符不能形成一个回文，这个时候要看s[i+1...j]与s[i...j-1]这两段，
         * 因为s[i+1]可能与s[i+2...j]范围内的某个字符相同，拼凑出回文，因为s[i]!=s[j],同理可得
         * s[i...j-1]这段，
         * 故此，dp[i][j]=max[dp[i][j-1],dp[i+1][j]]
         */
        for (int i=len-1;i>=0;i--){
            dp[i][i]=1;
            //dp[i][j]规定了i<=j
            for (int j=i+1;j<len;j++){
                if (s.charAt(j)==s.charAt(i))
                    dp[i][j]=dp[i+1][j-1]+2; //由式子可知，在求dp[0][len-1]之前或者需要先求dp[1][len-2]，所以外圈需要从大的开始
                else
                    dp[i][j]=Math.max(dp[i][j-1],dp[i+1][j]);
            }
        }
        return dp[0][len-1];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            String s=scanner.next();
            int res=new Solution().longestPalindromeSubseq(s);
            System.out.println(res);
        }
    }
}
