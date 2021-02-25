package DP系列.数位DP.windy数;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/27 13:08
 * @Description: 扩充dp维度做法
 * @Url: https://www.luogu.com.cn/problem/P2657#submit
 * @限制: 1≤a≤b≤2×10^9
 * @Level:
 */
public class Main2 {
    //2^63:9223372036854775807有19位数
    private static long[] digit = new long[20];
    //int curPos, int preSelectNum, int ifZero, int curLimit
    private static long[][][][] dp = new long[20][10][2][2];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        System.out.println(solve(b) - solve(a - 1));
    }

    /**
     * 统计[0,num]有多少个数字是windy数
     * @param num
     * @return
     */
    private static long solve(long num) {
        int k = 0; //记录有多少数位
        while (num != 0) {
            digit[++k] = num % 10;
            num /= 10;
        }
        return dfs(k, -2, 1,1);
    }

    private static long dfs(int curPos, int preSelectNum, int ifZero, int curLimit) {
        if (curPos == 0) return 1;

        if (preSelectNum != -2 && dp[curPos][preSelectNum][ifZero][curLimit] != 0)
            return dp[curPos][preSelectNum][ifZero][curLimit];

        long up_bound = curLimit == 1 ? digit[curPos] : 9;
        long cnt = 0;

        for (int curSelect = 0; curSelect <= up_bound; curSelect++) {
            if ((Math.abs(curSelect - preSelectNum) < 2)) continue;
            cnt += dfs(curPos - 1, (ifZero == 1 && curSelect == 0) ? -2 : curSelect, ifZero == 1 && curSelect == 0 ? 1 : 0, curLimit == 1 && (curSelect == up_bound) ? 1 : 0);
        }
        if (preSelectNum != -2)
            dp[curPos][preSelectNum][ifZero][curLimit] = cnt;
        return cnt;
    }
}
