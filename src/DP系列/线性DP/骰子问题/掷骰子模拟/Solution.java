package DP系列.线性DP.骰子问题.掷骰子模拟;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/26 15:52
 * @Description:
 * 给你一个长度为6的数组和一个序列长度n, 序列由1-6组成，可重复。数组为对序列的约束，
 * 数组下标i(从1开始)表示序列中的数字i不能连续出现rollMax[i]次。如果两个序列中至少存在一个元素不同，
 * 就认为这两个序列是不同的。求不同点数序列的数量
 * @Url: https://leetcode-cn.com/problems/dice-roll-simulation/
 * @限制:
 * 1 <= n <= 5000
 * rollMax.length == 6
 * 1 <= rollMax[i] <= 15
 * @Level:
 */
public class Solution {

    private static final int MOD = 1_000_000_000 + 7;
    /**
     * @param n
     * @param rollMax
     * @return
     */
    public int dieSimulator(int n, int[] rollMax) {
        int max_con = 0; //最大连续出现的次数
        for (int r : rollMax) max_con = Math.max(r, max_con);
        /**
         * dp[time][num][con] = sum表示第time次掷骰子掷出数字num时，数字num连续出现con次的有效的不同的组合数量为sum
         * 这里统计的有效的
         * time范围为[1,6],
         * num范围为[1,6]
         * 并且题目要求的组合数量必须满足rollMax数组约束,那么所有的dp值都要求要满足约束
         * 那么由于每个dp状态都跟掷出数字num有关，只有掷出了num才会有"连续"之说，才会触发数组约束，进而更新数字num连续出现con次的有效的不同的组合数量的值
         * 如何根据上一次点数来判断这次投骰子后各值的变化呢？
         * 1、如果上一次出现的点数与这一次相同，那么该点数在这次投掷后已连续出现con次的情况的个数，
         * 等于该点数在上一次投掷后已连续出现con-1次的情况，因为由前者就能推导出后者了，
         * 即dp[time][num][con] = dp[time-1][num][con - 1],当然前提是con必须在1 ~ rollMax[j - 1]范围内，
         * 如果某个点数最多只能连续出现2次，计算其连续出现3次的情况是没有意义的，该情况个数必定为0。
         * 2、 如果上一次出现的点数与这一次不同，那么这次的点数出现的连续次数必然是1，
         * 那么该点数在这次投掷后已连续出现1次的情况的个数dp[time][num][1]，
         * 就等于上一次出现的点数及该点数所有可能的已连续出现次数对应的情况的个数的总和，因为前者就包括了后者的现象。
         * 最后返回所有骰子，在连续次数的约束范围内，所有可能的连续次数下各种投的情况总和
         */
        int[][][] dp = new int[n + 1][7][max_con + 1];

        for (int time = 1; time <= n; time++) { //第time次掷骰子
            for (int num = 1; num <= 6; num++) { //第time次出现的点数为num
                if (time == 1) { //如果第一次投的是num了，情况假设只有一种，初始化
                    dp[time][num][1] = 1;
                    continue;
                }
                for (int con = 1; con <= rollMax[num - 1]; con++) { //num的合法连续出现次数,con>1表示跟前面投的一样,con=1表示不一样
                    if (con > 1)
                        dp[time][num][con] = dp[time - 1][num][con - 1] % MOD;
                    else {
                        int s = 0;
                        for (int lastNum = 1; lastNum <= 6; lastNum++) {
                            if (lastNum == num) continue;
                            for (int lastCon = 1; lastCon <= rollMax[lastNum - 1]; lastCon++) {
                                s = (s + dp[time - 1][lastNum][lastCon]) % MOD;
                            }
                        }
                        dp[time][num][con] = s;
                    }
                }
            }
        }

        int res = 0;
        for (int num = 1; num <= 6; num++) {
            for (int con = 1; con <= rollMax[num - 1]; con++) {
                res = (res + dp[n][num][con]) % MOD;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
