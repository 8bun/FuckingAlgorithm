package DP系列.状态压缩DP.N天后的牢房;
import java.util.Scanner;
/**
 * @Author: Jack
 * @Date: 2020/6/15 17:08
 * @Description:
 * 首尾2个牢房最终会变成0，所以8个牢房实际上求的是6个牢房，组合值最多为64种可能，做一个大的循环，如果判断某一个值出现2次，
 * 那后面基本就会按照这个规律来回出现，将剩余的次数模上周期，就意味着在周期里面的值是不变的，只需要计算余数的值即可。
 * 如天数为7，则余数为7，此时只需要list.get(6)即可
 * 最终计算的周期为14。
 * 1 18
 * 2 82
 * 3 114
 * 4 34
 * 5 42
 * 6 62
 * 7 28
 * 8 72
 * 9 74
 * 10 78
 * 11 68
 * 12 84
 * 13 124
 * 14 56
 * 15 18
 * @Url: https://leetcode-cn.com/problems/prison-cells-after-n-days/
 * @限制:
 * cells.length == 8
 * cells[i] 的值为 0 或 1
 * 1 <= N <= 10^9
 * @Level: 
 */
public class Solution {

    private static final int T = 14;
    public int[] prisonAfterNDays(int[] cells, int N) {
        int pos = N % T;
        if (pos == 0) pos = T; //余数为0时，应该是求dp[14]
        int[] dp = new int[pos + 1];
        for(int i = 0; i < 8; i++){
            if (cells[i] == 1)
                dp[0] += (1 << (7 - i));
        }

        for(int d = 1; d <= pos; d++) {

            for (int i = 5; i >= 0; i--) {
                int l = (1 << (i + 2)) & dp[d - 1];
                int r = (1 << i) & dp[d - 1];
                if((l != 0 && r != 0) || (l == 0 && r ==0)){
                    dp[d] += (1 << (i + 1));
                }
            }
        }
        int[] res = new int[8];
        res[0] = 0;
        res[7] = 0;
        for (int i = 6; i >= 1; i--) {
            int cur = (1 << i) & dp[pos];
            if (cur != 0)
                res[7 - i] = 1;
            else res[7 - i] = 0;
        }
        return res;
    }
    public static void main(String[] args){
         Scanner scanner=new Scanner(System.in);
         int[] cells = {1,0,0,1,0,0,1,0};
         int[] res = new Solution().prisonAfterNDays(cells, 30);
         for (int i : res) {
             System.out.print(i + " ");
         }
        System.out.println();
    }
}
