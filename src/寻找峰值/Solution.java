package 寻找峰值;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/15 16:54
 * @Description:
 * @Url: https://leetcode-cn.com/problems/find-peak-element/
 * @限制: 解法应该是 O(logN) 时间复杂度的，二分查找的时间复杂度是O(log2N)
 * @Level:
 */
public class Solution {

    public int findPeakElement(int[] nums) {
        return findPeakElement(nums,0,nums.length-1);
    }
    private int findPeakElement(int[] nums,int left,int right){
        if(left==right)
            return left;
        int mid=(left+right)>>1;
        if(nums[mid]>nums[mid+1])
            return findPeakElement(nums,left,mid); //目标在左边
        else return findPeakElement(nums,mid+1,right);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
