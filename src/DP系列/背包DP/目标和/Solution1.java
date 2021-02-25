package DP系列.背包DP.目标和;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/12 23:18
 * @Description:
 * 这里采用01背包思想DP
 * 这道题可以非常巧妙的转化
 * 因为要用完所有的数字，可以将这个数组分成两个数组a和b
 * 满足
 * sum(a)+sum(b)=sum(nums)
 * sum(a)-sum(b)=S
 * a代表所有非负数组，b代表所有非正数组，
 * 一正一负，正的和负的绝对值为总和，正的-负的为我们的目标S
 * 那么可以求得sum(a) = (sum(nums)+S)/2
 * 即在数组nums中取子集，满足子集的和为(sum(nums)+S)/2，看看这样的条件有多少种，
 * 要求一个元素只能用一次，进而转化为0-1背包组合问题
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution1 {

    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum < S || (sum + S) % 2 != 0)
            return 0;
        int target = (sum + S)/2;
        int[] dp = new int[target+1]; //dp[i]表示在sum=i时选取nums数组元素凑成和i的方案数
        dp[0] = 1;//target=0;方案数只有一种就是不选择任何元素
        for (int i = 0; i < nums.length; i++){
            for (int w = target; w >= nums[i]; w--)
                dp[w] += dp[w-nums[i]];
        }
        return dp[target];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
