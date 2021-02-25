package 绝对差不超过限制的最长连续子数组;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/3 12:49
 * @Description:
 * @Url: https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 * @限制:
 * @Level:
 */
public class Solution {

    public int longestSubarray(int[] nums, int limit) {
        int left=0,right=0;
        int res=0;
        int[] tmpArr = Arrays.copyOf(nums,nums.length);
        Arrays.sort(tmpArr);
        if (tmpArr[0]==tmpArr[tmpArr.length-1])
            return tmpArr.length;
        for (;right<nums.length;right++){
            if (getSubforMaxAndMin(nums,left,right)>limit){
                left++;
            }
            else {
                res=Math.max(right-left+1,res);
            }
        }
        return res;
    }
    private int getSubforMaxAndMin(int[] nums,int from,int to){
        int[] num =new int[to-from+1];
        for (int i=from;i<=to;i++)
            num[i-from]=nums[i];
        Arrays.parallelSort(num);
        return num[num.length-1]-num[0];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
