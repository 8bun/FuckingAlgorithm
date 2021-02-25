package DP系列.背包DP.一和零;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/13 11:21
 * @Description: 多维费用的01背包问题(二维)
 * 这道题和经典的背包问题很类似，不同的是在背包问题中，我们只有一种容量，
 * 而在这道题中，我们有 0 和 1 两种容量。每个物品（字符串）需要分别占用 0 和 1 的若干容量，
 * 并且所有物品的价值均为 1，所以问题转化为在几种物品中进行选择，每种物品只能选一个，求
 * 选出的物品能"刚好装满"背包容量(若干个0和若干个1)的时的最大价值。
 * 因此我们可以使用二维的动态规划(01背包)
 * 我们用 dp(i, j) 表示使用 i 个 0 和 j 个 1，最多能拼出的字符串数目
 * @Url: https://leetcode-cn.com/problems/ones-and-zeroes/
 * @限制:
 * @Level:
 */
public class Solution1 {

    public int findMaxForm(String[] strs, int m, int n) {
         //背包容量m个0，n个1
        int[][] dp = new int[m+1][n+1];
        dp[0][0] = 0;
        for (String str : strs){
            int one = countOne(str);
            int zero = str.length() - one;
            //选择一个背包（能装得下one,zero重的物品）
            //其实这里是三维优化成了二维，从前往后就会覆盖掉上一维度的数据，算出来的值就是错误的数据
            for (int i = m; i >= zero; i--){
                for (int j = n; j >= one; j--){
                    dp[i][j] = Math.max(dp[i][j], dp[i-zero][j-one] + 1);
                }
            }
        }
        return dp[m][n];
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
