package DP系列.递推型DP.第N个泰波那契数;import java.util.Scanner;
/**
 * @Author: Jack
 * @Date: 2020/6/15 19:16
 * @Description: T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 * @Url: 
 * @限制:
 * 0 <= n <= 37
 * 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
 * @Level: 
 */
public class Solution {

    public int tribonacci(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 1;

        long[] dp = new long[n + 1];
        dp[0] = 0; dp[1] = 1; dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return (int) dp[n];
    }
    public static void main(String[] args){
         Scanner scanner=new Scanner(System.in);
    }
}
