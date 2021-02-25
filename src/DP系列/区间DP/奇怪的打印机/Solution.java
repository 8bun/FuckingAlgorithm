package DP系列.区间DP.奇怪的打印机;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/12 10:47
 * @Description:
 * @Url: https://leetcode-cn.com/problems/strange-printer/
 * @限制:
 * @Level:
 */
public class Solution {

    public int strangePrinter(String s) {
        int len=s.length();
        if (len<=1)return len;
        int[][] dp=new int[len][len];//dp[i][j]代表的是s[i, j]段打印的最少次数
        for (int i=0;i<len;i++)dp[i][i]=1; //只有一个字符，只需一次打印
        /**
         * 打印s[i, j]这段共有两种情况
         * 第一种：dp[i][j] = 1 + dp[i + 1][j]; //i单独打印， s[i + 1, j]段另外打印，这是最坏情况，因为它单独打印了，如果后面还有跟它一样的字符，本来它们可以一起打印的（算一次），这样后面的字符就得多打印一次
         * 第二种：dp[i][j] = min(dp[i][j], dp[i + 1][k-1] + dp[k][j]);(k的范围为[i+1,j])//当s[i] == s[k]时，dp[i + 1][k-1]代表将i放到后面的前半部分[i+1, k-1]一起打印，dp[k][j]代表[k, j]另外打印
         */
        for (int i=len-1; i>=0; i--){
            for (int j=i+1; j<len; j++){ //保证[i..j]长度至少为2
                dp[i][j]=dp[i+1][j]+1; //最坏的情况,s[i]自己单独打印一次
                //其他情况，尝试跟s[i]后面的一起打印
                for (int k=i+1; k<=j; k++){  //遍历[i+1,j]区间
                    ////当s[i] == s[k]时，dp[i + 1][k-1]代表将i放到后面的前半部分[i+1, k-1]一起打印，dp[k][j]代表[k, j]另外打印
                    if (s.charAt(i)==s.charAt(k)){
                        dp[i][j]=Math.min(dp[i][j],dp[i+1][k-1]+dp[k][j]);
                    }
                }
            }
        }
        return dp[0][len-1];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
