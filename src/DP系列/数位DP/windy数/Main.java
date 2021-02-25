package DP系列.数位DP.windy数;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/27 13:08
 * @Description:
 * 不含前导零且相邻两个数字之差至少为 2 的正整数被称为 windy 数。
 * 求出在[a,b]内总共有多少个 windy 数
 * @Url: https://www.luogu.com.cn/problem/P2657#submit
 * @限制: 1≤a≤b≤2×10^9
 * @Level:
 */
public class Main {
    //2^63:9223372036854775807有19位数
    private static long[] digit = new long[20];
    //int curPos, int preSelectNum
    private static long[][] dp = new long[20][10];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        for (long[] x : dp) Arrays.fill(x, - 1);
        System.out.println(solve(b) - solve(a - 1));
    }

    /**
     * 统计[0,num]有多少个数字是windy数
     * @param num
     *
     * @return
     */
    private static long solve(long num) {
        int k = 0; //记录有多少数位
        while (num != 0) {
            digit[++k] = num % 10;
            num /= 10;
        }
        //最高位一定要限制,这里一开始preSelectNum=-2是防止进入
        //dfs后被(Math.abs(curSelect - preSelectNum) < 2))限制选择，因为
        //高位实际上不用考虑windy数的限制,当curSelect选到最小0也不能满足上面的式子
        //开始ifZero设置为true是为了使得如果高位也选了0那么传给下一位的ifZero为true
        //也就是(ifZero && curSelect == 0)为true
        return dfs(k, -2, true,true);
    }

    /**
     * dp[i][j] 表示搜到第 i 位，在前驱是 j 的情况下,的最多方案数目
     * @param curPos
     * @param preSelectNum 前驱，即上一位是谁
     * @param ifZero ifZero为true，表示curPos前面都是零，反过来，ifZero为false，表示没有前导零
     * @param curLimit
     * @return
     */
    private static long dfs(int curPos, int preSelectNum, boolean ifZero, boolean curLimit) {
        if (curPos == 0) return 1;
        /**
         * 如num=5123
         *  那么11__中不包含windy数的个数等于[2-4]1__中不包含windy数的个数
         *  因为后面都只有00-99这些选择
         *  如果是00__这样的就不必要进行记忆，如果进行记忆，因为我设置的状态dp是记忆没有
         *  包含前导0的，所以如果用我的dp进行记忆的话，那么它的状态是跟10__一样的
         *  可是,00__的当前位不受限制，可以有0-9 10种选择，10__则只有0-2 3种选择（差值不超过2）
         *  这就不一样了。
         *  当然，如果要进行记忆，也可以扩充dp一维来记录这个ifZero状态，然后这里判断就不用卡isZero了
         *  对curLimit也是同样的道理
         */
        if (!curLimit && !ifZero  && dp[curPos][preSelectNum] != - 1)
            return dp[curPos][preSelectNum];

        long up_bound = curLimit ? digit[curPos] : 9;
        long cnt = 0; //统计当前位在[0,up_bound]内所有选择情况下包含windy数的数字个数

        for (int curSelect = 0; curSelect <= up_bound; curSelect++) {
            if ((Math.abs(curSelect - preSelectNum) < 2)) continue; //每次都在这里剪去不是windy数(差值>=2)的分支
            //这里要注意"前导0"思想对curSelect的传递影响
            cnt += dfs(curPos - 1, (ifZero && curSelect == 0) ? -2 : curSelect, ifZero && curSelect == 0, curLimit && (curSelect == up_bound));
        }

        /**
         * 没有最高位限制且没有前导0时记录结果
         */
        if (!curLimit && !ifZero) dp[curPos][preSelectNum] = cnt;
        return cnt;
    }
}
