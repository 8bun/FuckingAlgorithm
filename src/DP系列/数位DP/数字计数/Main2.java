package DP系列.数位DP.数字计数;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/27 15:37
 * @Description: 二维
 * @Url: https://www.luogu.com.cn/problem/P2602
 * @限制: 1≤a≤b≤10^12
 * @Level:
 */
public class Main2 {
    private static long[] digit = new long[20];

    private static long[][] dp = new long[20][20];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();

        for (int curDigit = 0; curDigit <= 9; curDigit++) {
            long s = solve(b, curDigit) - solve(a - 1, curDigit);
            if (curDigit == 9)
                System.out.println(s);
            else
                System.out.print(s+" ");
        }
    }


    private static long solve(long num, int curDigit) {
        int k = 0; //记录有多少数位
        while (num != 0) {
            digit[++k] = num % 10;
            num /= 10;
        }
        return dfs(k, curDigit, 1, 1, 0) ;
    }

    private static long dfs(int curPos, int curDigit, int ifZero, int curLimit, int sum) {

        if (curPos == 0) return sum;
        /**
         * 例如num=5123, 则统计到21__、13__的中前面2位中1的次数是一样的，都是1次，且当前curPos相同（limit=0,ifZero=0）
         * 所以，这里要记录的状态应该是跟curPos和sum有关的
         */
        if (curLimit != 1 && ifZero != 1 && dp[curPos][sum] != 0)
            return dp[curPos][sum];

        long up_bound = curLimit == 1 ? digit[curPos] : 9;
        long cnt = 0;

        for (int curSelect = 0; curSelect <= up_bound; curSelect++) {

            if (ifZero == 1 && curSelect == 0)
                cnt += dfs(curPos - 1, curDigit, 1, curLimit == 1 && curSelect == up_bound ? 1 : 0, sum);
            else
                cnt += dfs(curPos - 1, curDigit, 0, curLimit == 1 && curSelect == up_bound ? 1 : 0, sum + (curSelect == curDigit ? 1 : 0));
        }
        if (curLimit != 1 && ifZero != 1)
            dp[curPos][sum] = cnt;
        return cnt;
    }
}

