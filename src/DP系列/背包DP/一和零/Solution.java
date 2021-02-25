package DP系列.背包DP.一和零;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/13 14:32
 * @Description:
 * dp[i][j][k] 表示字符串数组在区间 [0, i-1] 能够凑成 j个0 和 k个1 的字符串的最大数量
 * @Url:
 * @限制:
 * @Level:
 */

public class Solution {

    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len+1][m + 1][n + 1]; // dp[i][j][k] 表示字符串数组在区间 [0, i-1] 能够凑成 j个0 和 k个1 的字符串的最大数量

        for (int i = 1; i < len; i++){

            int one = countOne(strs[i-1]);
            int zero = strs[i-1].length() - one;

            for (int j = 0; j < m; j++){
                for (int k = 0; k < n; k++){

                    dp[i][j][k] = dp[i - 1][j][k];  //最坏情况，不参与组合（少了一个）
                    if (j >= zero && k >= one)
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zero][k - one] + 1);
                }
            }
        }
        return dp[len][m][n];
    }
    /**
     * 统计字符串str中的1的个数
     * @param string
     * @return
     */
    private int countOne(String string){
        if ("".equals(string))return 0;
        int sum=0;
        for (char ch : string.toCharArray()){
            sum+=(ch-'0');
        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
