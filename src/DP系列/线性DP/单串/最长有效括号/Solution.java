package DP系列.线性DP.单串.最长有效括号;
import java.util.Scanner;
/**
 * @Author: Jack
 * @Date: 2020/7/4 15:05
 * @Description:
 * dp [ i ] 代表以下标i处字符结尾的合法序列（有效括号的连续字符串）的最长长度
 * 注意这个定义，如s="()(()"，那么dp的值将是0 2 0 0 2
 * @Url: https://leetcode-cn.com/problems/longest-valid-parentheses/
 * @限制: 
 * @Level: 
 */
public class Solution {

    public int longestValidParentheses(String s) {
        int res = 0;
        int len = s.length();
        int[] dp = new int[len];
        for (int i = 1; i < len; i++) {
            /**
             * 如果形如"....()"，那么dp[i]=dp[i-2]+2，后面2个构成一对
             * 如果形如"....))"，那么由于dp[i-1]表示以s[i-1]为结尾的‘有效括号’长度为dp[i-1]
             * 也就是s[i]之前dp[i-1]长度的字符串，那么这个长度的字符串前面一个字符也就是s[i-dp[i-1]-1]如果是'('
             * 那么就可以与s[i]的')'匹配，此时dp[i] = dp[i-1] + dp[i - dp[i - 1] - 2] + 2;
             * 如果不是'('那就为0，因为dp[i-1]不能与s[i]组成有效括号子串
             * 如果s[i]=='('，那以它结尾的有效括号子串长度为0
             */
            if (s.charAt(i) == ')') {
                if(s.charAt(i - 1) == '(') {
                    dp[i] = i - 2 >= 0 ? dp[i - 2] + 2 : 2;
                }
                else {
                    if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    else dp[i] = 0;
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args){
         Scanner scanner=new Scanner(System.in);
         String s = "()(()";
         int res = new Solution().longestValidParentheses(s);
         System.out.println(res);
    }
}
