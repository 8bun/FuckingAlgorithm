package DP系列.博弈型DP.石子游戏II;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/7/7 17:00
 * @Description: 与前面的记忆化递归相同，这里采用 dp[start][m]表示当前剩余未选择的石子从下标start开始时，
 * M为m的情况下第一位抓的玩家所能获取的最大数量石头
 * 这里采用dp的思想
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution1 {

    public int stoneGameII(int[] piles) {
        int len = piles.length;
        int[][] dp = new int[len][len];
        int[] sum = new int[len];

        //初始化后缀和,sum[i]表示从piles[i]到piles[len-1]的和
        sum[len - 1] = piles[len - 1];
        for (int i = len - 2; i >= 0; i--) sum[i] = sum[i + 1] + piles[i];

        int ans = 0;
        //由记忆化递归可知，需要先知道start更大的值
        for (int start = len - 1; start >= 0; start--) {
            for (int m = 1; m < len; m++) {
                if (start + 2 * m >= len) {
                    dp[start][m] = sum[start];
                    continue;
                }
                for (int select = 1; select <= 2 * m; select++) {
                    //dp[start + select][Math.max(m, select)]表示下一位玩家的从start+select开始，m改变为Math.max(m, select)时选择的最大量
                    //sum[start] - dp[start + select][Math.max(m, select)]一直取最大值作为dp[start][m]的值即可
                    dp[start][m] = Math.max(dp[start][m], sum[start] - dp[start + select][Math.max(m, select)]);
                }
            }
        }
        return dp[0][1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
