package DP系列.数位DP.萌数;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/27 20:56
 * @Description:
 * 找出[l,r]中 “存在长度至少为2的回文子串”的数的个数
 * 例如101是萌的，因为101本身就是一个回文数；110是萌的，因为包含回文子串11；但是102不是萌的，1201也不是萌的。
 * @Url: https://www.luogu.com.cn/problem/P3413
 * @限制:
 * 记n为r在10进制下的位数, 对于全部的数据，n <= 1000，l < r
 * @Level:
 */
public class Main {
    private static final int MOD = 1_000_000_000 + 7;
    private static long[] digit = new long[1001];
    //int curPos, long pre2, long pre1
    private static long dp[][][] = new long[1001][10][10];

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String l = scanner.next();
        String r = scanner.next();
        long s1=l.length();
        long s2=r.length();
        long numl = 0;
        //将l从字符串形式转化为MOD后的long形式
        for(int i = 0;i < s1; i++) {
            numl = (numl * 10 % MOD + (l.charAt(i) - '0')) % MOD;
        }
        //将r从字符串形式转化为MOD后的long形式
        long numr = 0;
        for(int i = 0; i < s2; i++) {
            numr = (numr * 10 % MOD + (r.charAt(i) - '0')) % MOD;
        }
        //最终结果应该是：numr-(numl - 1)-[solve(r)-solve(numl-1的字符串形式)]
        //由于l可能有1000位，并且输入l为字符串形式，那么求出numl-1的字符串形式从难度上（精度上）就非常大，
        //即[numl,numr]区间的萌数个数res = [numl,numr]的区间长度 - [numl,numr]区间的非萌数个数.
        //而如果l是非萌数，那么[numl,numr]区间的萌数个数=[numl+1,numr]的区间长度 - [numl+1,numr]区间的非萌数个数
        //也就是res=numr-numl-[solve(r) - solve(l)]
        //所以我们可以先求出(numl,numr]区间的萌数个数，然后单独判断numl是否是萌数来决定是否要+1
        // 求余和取模是有区别的: a%b https://blog.csdn.net/creat2012/article/details/17999929
        // 简单来说，求余的结果应该与a的符号保持一致；
        // 而取模的结果应该与b的符号保持一致。
        //为了防止出现负数，这里用到了：
        //((a%b)+b)%b ,即将一个负数取模后转换为正数(同余定理)
        System.out.println(((numr - numl - (solve(r) - solve(l)) + check(l))% MOD + MOD) % MOD);
    }

    private static long check(String x) {
        int len = x.length();
        for (int i = 1; i < len; i++) if (x.charAt(i) == x.charAt(i - 1)) return 1;
        for (int i = 2; i < len; i++) if (x.charAt(i) == x.charAt(i - 2)) return 1;
        return 0;
    }

    private static long solve(String l) {
        /**
         * num=1234,digit[]={4,3,2,1};
         */
        int len = l.length();
        for (int i = 0; i < len; i++) {
            digit[len - i] = l.charAt(i) - '0';
        }

        return dfs(len,-1, -1, true, true);
    }
    /**
     * 分析可得，所有的回文数都必定可以简化为 aa 和 aba 中的一种
     * 也就是说，发现当一个数的所有位都与其前两位的数字都不相同时，这个数就不含回文串.
     * 所以我们从反面来求解。
     * 那么dp就可以记录当前位置，前面一位为pre1，前面第二位为pre2
     * 哪些情况会导致重复计算呢？
     * 例如l=1,r=5123
     * 首先，我们dfs剪去了萌数的分支（所以不能出现44__之类的萌分支），那么对于下面进入非萌分支判断的情况中，例如
     * 12__和43__的以12和43开头的数字中 所包含的非萌数个数是一样的因为后面都可以取00-99
     * 对于12__，第三位可取3-9,有6种选择，第四位根据第三位作出选择，才能构成非萌数
     * 对于43__，第三位可取1-2,5-9，有6种选择，第四位也是根据第三位作出选择
     * 所以它们总数是一样的
     * 我们发现当前位都是3,并且curLimit==false,ifZero=false，可对这种状态下的dp进行非萌数个数的记忆
     */
    private static long dfs(int curPos, int pre2, int pre1, boolean curLimit, boolean ifZero){
        if (curPos == 0) return 1;

        if (pre1 != -1 && pre2 != -1 && !curLimit && !ifZero && dp[curPos][pre2][pre1] != 0) return dp[curPos][pre2][pre1];

        long up_bound = curLimit ? digit[curPos] : 9;
        long cnt = 0;

        for (int curSelect = 0; curSelect <= up_bound; curSelect++) {
            if (curSelect == pre1 || curSelect == pre2) continue; //剪去萌分支
            if (ifZero && curSelect == 0)
                cnt = (cnt + dfs(curPos - 1, -1, -1, false, true)) % MOD;
            else
                cnt = (cnt + dfs(curPos - 1, pre1, curSelect, curLimit && curSelect == up_bound,false))%MOD;
        }
        if (pre1 != -1 && pre2 != -1 && !curLimit && !ifZero)
            dp[curPos][pre2][pre1] = cnt;
        return cnt;
    }
}
