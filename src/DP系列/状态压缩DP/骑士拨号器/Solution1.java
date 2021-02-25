package DP系列.状态压缩DP.骑士拨号器;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/15 16:50
 * @Description:
 * 记忆化回溯
 * 7 -> 2, 6
 * 9 -> 4, 2
 * 由上面两个例子可知，2可以到7和9，所以在动态规划中求如dp[N-1][7]和dp[N-1][9]时
 * 都需要计算dp[N-2][2]的值，如果在递归里面不提前记忆dp[N-2][2]的值
 * 就有可能造成了重复计算（在动规里面不会，但是这里使用递归，所以还是需要用一个数据结构来记忆）
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution1 {

    private int[][] path = {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {3, 9, 0}, {}, {1, 7, 0}, {2, 6}, {1, 9}, {4, 2}};
    private static final int MOD = 1000000007;

    public int knightDialer(int N) {
        int[][] memo = new int[N][10];
        int res = 0;
        for (int num = 0; num < 10; num++)
            res = (res + helper(N - 1, num, memo, path)) % MOD;
        return res;
    }

    /**
     * 求第n次跳到num时组成的不同号码的个数
     * @param n
     * @param num
     * @param memo
     * @param path
     * @return
     */
    private int helper(int n, int num, int[][] memo, int[][] path) {
        if (n == 0) return 1;
        if (memo[n][num] != 0) //如果已经算过
            return memo[n][num];
        int res = 0;
        for (int arrive : path[num])
            res = (res + helper(n - 1, arrive, memo, path)) % MOD;
        return memo[n][num] = res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
