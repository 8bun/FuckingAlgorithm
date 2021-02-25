package DP系列.计数型DP.不同路径;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/20 09:26
 * @Description: 求从左上角到达右下角的路径数，每一次能够向下或向右走
 * @Url: https://leetcode-cn.com/problems/unique-paths/
 * @限制:
 * @Level:
 */
public class Solution {

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++)
            dp[0][i] = 1;
        for (int i = 0; i < m; i++)
            dp[i][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
