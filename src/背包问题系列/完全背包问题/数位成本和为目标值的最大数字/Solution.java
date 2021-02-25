package 背包问题系列.完全背包问题.数位成本和为目标值的最大数字;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/17 10:13
 * @Description: 完全背包的刚好装满问题
 * @Url: https://leetcode-cn.com/contest/biweekly-contest-26/problems/form-largest-integer-with-digits-that-add-up-to-target/
 * @限制:
 * cost.length == 9
 * 1 <= cost[i] <= 5000
 * 1 <= target <= 5000
 * @Level:
 */
public class Solution {

    public String largestNumber(int[] cost, int target) {
        //target>=1，当cost[i]都等于1时，target位数最大，所以开target+1的大小
        //dp[i]表示背包重为i时，刚好装满时的最大数字
        String[] dp = new String[target + 1];
        Arrays.fill(dp, "*");  //dp[i]="*"表示当前背包容量为i时的最大数字还没确定，
        dp[0] = ""; //背包容量为0，最大数字为""
        for (int i = 0; i < cost.length; i++){
            for (int j = cost[i]; j <= target; j++){
                //后面的表达式需要dp[j - cost[i]]的值，这个值不能无效
                //例如，包容量为10，选择装入重量为3的数字，那么容量为7的包装满的最大数字应该先有效
                //这样才保证了dp[10]是有效的,这个等式就是为了得到选择装入的时候的最大值进行比较
                if(dp[j - cost[i]].equals("*")) continue;
                //包容量为j, 不选择或选择重量i+1装入包的最大数
                dp[j] = Solution.maxStr(dp[j], (i+1) + "" + dp[j - cost[i]]);
            }
        }
        return dp[target].equals("*") ? "0" : dp[target];
    }

    /**
     * 字符串比较
     * @param a
     * @param b
     * @return
     */
    private static String maxStr(String a, String b) {
        if (a == null) return b;
        if (a.length() == b.length()) {
            return a.compareTo(b) > 0 ? a : b;
        }
        return a.length() > b.length() ? a : b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] cost = {4,3,2,5,6,7,2,5,5};
        String res = new Solution().largestNumber(cost, 9);
        System.out.println(res);
    }

}
