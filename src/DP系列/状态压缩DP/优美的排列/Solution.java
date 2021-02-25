package DP系列.状态压缩DP.优美的排列;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/14 18:01
 * @Description:
 * 回溯暴力,适当剪枝
 * @Url: https://leetcode-cn.com/problems/beautiful-arrangement/
 * @限制:
 * @Level:
 */
public class Solution {

    private int res = 0;
    private  boolean[] vis;
    public int countArrangement(int N) {

        vis = new boolean[N+1];

        backtrace(0, N);
        return res;
    }
    private void backtrace(int toFindIndex, int N){

        if (toFindIndex == N) {
            res++; return;
        }

        for (int num = 1; num <= N; num++){
            if (!vis[num]) {
                vis[num] = true;
                if (num % (toFindIndex + 1) == 0 || (toFindIndex + 1) % num == 0)
                    backtrace(toFindIndex + 1, N);
                vis[num] = false;
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = 3;
        int res = new Solution().countArrangement(N);
        System.out.println(res);
    }
}
