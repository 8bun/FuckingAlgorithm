package DP系列.数位DP.最大为N的数字组合;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author: Jack
 * @Date: 2020/5/26 23:16
 * @Description: 数位dp做法
 * @Url:
 * @限制:
 * D 是按排序顺序的数字 '1'-'9' 的子集,不包括0
 * 1 <= N <= 10^9
 * @Level:
 */
public class Solution1 {

    private int[] digit = new int[11];
    private int[] dp = new int[11];
    private Set<String> set = new HashSet<>();
    public int atMostNGivenDigitSet(String[] D, int N) {
        Arrays.fill(dp, -1);
        set.addAll(Arrays.asList(D));
        return solve(N) - 1;
    }

    private int solve(int num) {
        int k = 0; //记录有多少数位
        while (num != 0){
            digit[++k] = num % 10;
            num /= 10;
        }
        return dfs(k, true, true); //最高位一定要限制
    }

    private int dfs(int curPos, boolean ifZero, boolean curLimit) {

        if (curPos == 0) return 1;

        if (!curLimit && !ifZero && dp[curPos] != -1) return dp[curPos];

        int max_num = curLimit ? digit[curPos] : 9;

        int cnt = 0;
        for (int select = 0; select <= max_num; select++) {
            if (select != 0 && !set.contains(String.valueOf(select))) continue;
            if (!ifZero && select == 0) continue;
            cnt += dfs(curPos - 1, ifZero && select == 0, curLimit && max_num == select);
        }
        if (!curLimit && !ifZero) dp[curPos] = cnt;
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] D = {"1","3","5","7"};
        int N = 100;
        int res = new Solution1().atMostNGivenDigitSet(D, N);
        System.out.println(res);
    }
}
