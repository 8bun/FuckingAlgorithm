package DP系列.线性DP.其他经典问题.打家劫舍II;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/10 10:05
 * @Description:
 * 要求第0家和第n-1家永远也不能同时被偷，所以考虑第0家的时候不要包含第n-1家，考虑第n-1家的时候不要包含第0家
 * 用这两个线性表分别去做198题的动态规划，取大的即可
 * @Url: https://leetcode-cn.com/problems/house-robber-ii/
 * @限制:
 * 此题中的房间是环状排列的（即首尾相接）
 * @Level:
 */
public class Solution {
    public int rob(int[] nums) {
        int len=nums.length;
        if (len==0)return 0;
        if (len==1)return nums[0];
        return Math.max(getMax(nums,0,len-2),getMax(nums,1,len-1));
    }

    /**
     * 从nums的下标from到end进行取最大，不考虑环型
     * @param nums
     * @param from
     * @param end
     * @return
     */
    private int getMax(int[] nums,int from,int end){
        int len = end-from+1;
        if (len == 0)
            return 0;
        if (len == 1)
            return nums[from];
        int[] dp = new int[len];
        dp[0] = nums[from];
        dp[1] = Math.max(nums[from+1], dp[0]);
        from+=2;
        for (int i = 2; from<=end; i++,from++) {
            dp[i] = Math.max(dp[i- 1], dp[i- 2] + nums[from]);
        }
        return dp[len-1];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums={1,3,1,3,100};
        int res=new Solution().rob(nums);
        System.out.println(res);
    }
}
