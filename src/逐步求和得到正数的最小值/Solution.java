package 逐步求和得到正数的最小值;

import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/4/18 22:38
 * @Description:
 * @link https://leetcode-cn.com/contest/biweekly-contest-24/problems/minimum-value-to-get-positive-step-by-step-sum/
 * @限制:
 * @Level:
 */
public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }

    public int minStartValue(int[] nums) {
        int startValue = 1;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            startValue = Math.max(startValue, 1 - sum);
        }
        return startValue;
    }
}
