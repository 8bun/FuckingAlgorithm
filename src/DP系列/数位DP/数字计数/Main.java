package DP系列.数位DP.数字计数;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/27 15:37
 * @Description:
 * 给出一段区间[a,b]，统计这个区间内0-9出现的次数.
 * 比如 10-19，1出现11次（10,11,12,13,14,15,16,17,18,19,其中11包括2个1)，其余数字各出现1次.
 * 从各位向前依次编号
 * 我们可以分别计算出数字0在[a,b]内出现的次数,可以分别计算出数字0在[0,a]和[0,b]中出现的次数，相减即可
 * 1出现的次数....
 * @Url: https://www.luogu.com.cn/problem/P2602
 * @限制: 1≤a≤b≤10^12
 * @Level:
 */
public class Main {
    //2^63:9223372036854775807有19位数
    private static long[] digit = new long[20];
    //int curPos,int curDigit,int sum; sum代表curPos之前的数位上放上了多少个curDigit。
    private static long[][][] dp = new long[20][10][20];

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
        if (curLimit != 1 && ifZero != 1 && dp[curPos][curDigit][sum] != 0)
            return dp[curPos][curDigit][sum];

        long up_bound = curLimit == 1 ? digit[curPos] : 9;
        long cnt = 0;

        for (int curSelect = 0; curSelect <= up_bound; curSelect++) {

            if (ifZero == 1 && curSelect == 0)
                cnt += dfs(curPos - 1, curDigit, 1, curLimit == 1 && curSelect == up_bound ? 1 : 0, sum);
            else
                cnt += dfs(curPos - 1, curDigit, 0, curLimit == 1 && curSelect == up_bound ? 1 : 0, sum + (curSelect == curDigit ? 1 : 0));
        }
        if (curLimit != 1 && ifZero != 1)
            dp[curPos][curDigit][sum] = cnt;
        return cnt;
    }
}

