package DP系列.线性DP.其他经典问题.乘积最大的子数组;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/15 12:46
 * @Description:
 * @Url: https://leetcode-cn.com/problems/maximum-product-subarray/submissions/
 * @限制:
 * @Level:
 */
public class Solution {

    public int maxProduct(int[] nums) {
        int len = nums.length;
        int[] maxV = new int[len];   //以nums[i]结尾的连续子数组的最大乘积
        int[] minV = new int[len];   //以nums[i]结尾的连续子数组的最小乘积
        maxV[0] = nums[0];
        minV[0] = nums[0];
        int res = maxV[0];
        //根据当前数字的正负来确定最大最小
        for (int i = 1; i < len; i++) {
            int cur_num = nums[i];
            /*
            例如:2 -1 7，访问到7正数，判断是否跟前面结合构成最值，还是重新开始
             */
            if (cur_num >= 0) {
                maxV[i] = Math.max(maxV[i - 1] * cur_num, cur_num);
                minV[i] = Math.min(minV[i - 1] * cur_num, cur_num);
            }
            /*
            例如:1 2 -6，访问到-6负数，判断是否跟前面结合构成最值，还是重新开始
             */
            else {
                maxV[i] = Math.max(minV[i - 1] * cur_num, cur_num);
                minV[i] = Math.min(maxV[i - 1] * cur_num, cur_num);
            }
            res = Math.max(res, maxV[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = {2, 3, -1, 4};
        int res = new Solution().maxProduct(nums);
        System.out.println(res);
    }
}
