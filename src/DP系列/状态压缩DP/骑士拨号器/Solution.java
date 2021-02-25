package DP系列.状态压缩DP.骑士拨号器;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/15 16:03
 * @Description:
 * 普通动态规划:
 * 每个数字及可达数字为：
 * 0 -> 4, 6
 * 1 -> 6, 8
 * 2 -> 7, 9
 * 3 -> 4, 8
 * 4 -> 3, 9, 0
 * 5 ->
 * 6 -> 1, 7, 0
 * 7 -> 2, 6
 * 8 -> 1, 9
 * 9 -> 4, 2
 *  设dp[time][num] 表示骑士第time次跳到数字num时组成的不同号码的个数
 *  那么要实现第time次跳到数字num,那么就要保证第time-1次跳到num的可达数字
 *  也就是说dp[time][num]是第time-1跳到num的所有可达数字的dp的总和
 *  最后返回要求dp[N-1][]的值
 * @Url: https://leetcode-cn.com/problems/knight-dialer/
 * @限制:
 * @Level:
 */
public class Solution {
    //行下标为起点num,每一行的所有数据为可达数字
    private int[][] path = {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {3, 9, 0}, {}, {1, 7, 0}, {2, 6}, {1, 9}, {4, 2}};
    private static final int MOD = 1_000_000_007; //这是Java7引入的新特性。分割数字增强可读性。当然也有限制：https://docs.oracle.com/javase/7/docs/technotes/guides/language/underscores-literals.html

    public int knightDialer(int N) {
        int[][] dp = new int[N][10];
        for (int num = 0; num < 10; num++)
            dp[0][num] = 1; //第0次（跳到）num的不同号码个数为1

        for (int time = 1; time < N; time++) {
            for (int num = 0; num < 10; num++){
                for (int arrive : path[num])
                    dp[time][num] = (dp[time][num] + dp[time - 1][arrive]) % MOD;
            }
        }
        int res = 0;
        for (int num = 0; num < 10; num++)
            res = (res + dp[N - 1][num]) % MOD;
        return res;
    }

    /**
     * 法2：滚动数组，先来看下面的位运算规律
     *  for (int hops = 1; hops <= 4; ++hops) {
     *     System.out.println(~ hops); //-2 -3 -4 -5
     *     System.out.println(~ hops & 1); //0 1 0 1
     *     System.out.println(hops & 1);   //1 0 1 0
     *  }
     *  为什么使用滚动数组？
     *  因为由dp[time][num] = (dp[time][num] + dp[time - 1][arrive]) % MOD;
     *  我们发现每一次的状态都跟前一步的状态有关系，即在求dp[time][]时，dp[0...time-2][]的数据都没有作用，此时只需要dp[time-1][]的数据
     *  所以我们的dp数组可以开为dp[2][10](也就是两行)，dp[0][]和dp[1][]用来交替地存当前状态和存上一个状态
     *  之所以是交替的，可以这样想，假设此时是dp[0][]存当前状态,dp[1][]存上一个状态，那么
     *  进入下一次循环时，dp[0][]对它来说就是最新的上一个状态，用它来更新dp[1][]当前状态
     *  这就好像数组“滚动”起来了。
     *
     *  滚动数组是DP中的一种编程思想。简单的理解就是让数组滚动起来，每次都使用固定的几个存储空间，来达到压缩，节省存储空间的作用。
     *  起到优化空间，主要应用在递推或动态规划中（如01背包问题）。
     *  因为DP题目是一个自底向上的扩展过程，我们常常需要用到的是连续的解，前面的解往往可以舍去。
     *  所以用滚动数组优化是很有效的。利用滚动数组的话在N很大的情况下可以达到压缩存储的作用。
     * 当然是用时间去换空间的
     * @param N
     */
    public int knightDialer1(int N) {
        int[][] dp = new int[2][10];
        Arrays.fill(dp[0], 1);
        //第0步跳到不同num的不同号码个数先存储在第0行，第一行接下来存跳第一步之后的结果(time=1)，
        //然后第0行根据第一行的结果存跳第二步之后的结果....,所以，步数 -> 结果存的行有如下规律:
        /**
         * 0 0
         * 1 1
         * 2 0
         * 3 1
         * ..
         * 所以第N-1步要看N-1是奇数还是偶数，奇数就存在第1行，偶数存在第0行
         */
        for (int time = 1; time < N; ++time) { //当time从1开始递增时，time&1为1 0 1 0..., ~time&1 为 0 1 0 1....
            Arrays.fill(dp[time & 1], 0);  //轮流更新dp[1][]和dp[0][]

            for (int num = 0; num < 10; num++) {
                for (int arrive : path[num])
                    dp[time & 1][num] = (dp[time & 1][num] + dp[~time & 1][arrive]) % MOD;
            }
        }
        int res = 0;
        /**
         * 第N-1步要看N-1是奇数还是偶数，奇数就存在第1行，偶数存在第0行
         * 即看N是是奇数还是偶数，奇数就存在第0行，偶数存在第1行
         * 由于当N从1(N>=1)开始递增时，也就是N分别为1,2,3,4时，~N&1分别为0 1 0 1（N为奇数则是0）
         * 那么我们使用~N&1的结果就可以求出N-1结果的所在的行
         */
        for (int x : dp[~N & 1])
            res = (res + x) % MOD;
        return res;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
