package DP系列.数位DP.不要62;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/27 12:30
 * @Description:
 * 输入的都是整数对n、m（0<n≤m<1000000），表示区间[n,m]
 * 求出在区间内不包含4或62的数的个数
 * 如果遇到都是0的整数对，则输入结束。
 * @Url: http://acm.hdu.edu.cn/showproblem.php?pid=2089
 * @限制:
 * @Level:
 */
public class Main {
    //2^63:9223372036854775807有19位数
    private static long[] digit = new long[20];
    private static long[][] dp = new long[20][2];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            long n = scanner.nextLong();
            long m = scanner.nextLong();
            if (n == 0 && m == 0) break;
            for(long[] x : dp) Arrays.fill(x, -1 );
            //[0,m]不包含-[0,n-1]不包含=[n,m]不包含
            System.out.println(solve(m) - solve(n - 1));
        }
    }

    /**
     * 统计[0,num]有多少个数字不包含62
     * @param num
     * @return
     */
    private static long solve(long num) {
        int k = 0; //记录有多少数位
        while (num != 0){
            digit[++k] = num % 10;
            num /= 10;
        }
        return dfs(k, 0, true); //最高位一定要限制
    }

    private static long dfs(int curPos, int ifLastSelect6, boolean curLimit) {
        if (curPos == 0) return 1;

        if (!curLimit && dp[curPos][ifLastSelect6] != -1)
            return dp[curPos][ifLastSelect6];

        long up_bound = curLimit ? digit[curPos] : 9;
        long cnt = 0; //统计当前位在[0,up_bound]内所有选择情况下不包含62的数字个数

        for (int curSelect = 0; curSelect <= up_bound; curSelect++){
            if ((ifLastSelect6 == 1 && curSelect == 2) || curSelect == 4) continue; //每次都在这里剪去62和4这一支
            cnt += dfs(curPos - 1, curSelect == 6 ? 1 : 0, curLimit && curSelect == up_bound);
        }

        /**
         * curLimit=true的情况，为什么不需要记忆？
         * 如num=5123, 当前位为最高位的下一位时，如果上一位选了5时, 就不用记录5___中不包含49的数字个数
         * 因为后面不会再有6了(6___)，也就是说没有情况数和它一样的数字表示（因为它的下一位只能选择0-1），不会
         * 导致重复计算的问题，所以没必要去记忆了
         */
        if (!curLimit) dp[curPos][ifLastSelect6] = cnt;
        return cnt;
    }
}

