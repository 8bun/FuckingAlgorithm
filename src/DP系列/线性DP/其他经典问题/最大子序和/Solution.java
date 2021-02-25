package DP系列.线性DP.其他经典问题.最大子序和;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/9 11:46
 * @Description:
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: "连续"子数组 [4,-1,2,1] 的和最大，为 6。
 * @Url: https://leetcode-cn.com/problems/maximum-subarray/
 * @限制:
 * @Level:
 */
public class Solution {
    public int maxSubArray(int[] nums) {
        int len=nums.length;
        if (len==0)
            return 0;
        int[] dp=new int[len]; //dp[i]表示以nums[i-1]为末尾的连续子序列的和的最大值
        dp[0]=nums[0];
        int max=dp[0];
        for (int i=1;i<len;i++){
            //要么从自己开始，要么跟前面连续
            dp[i]=Math.max(dp[i-1]+nums[i],nums[i]);
            max=Math.max(dp[i],max);
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
