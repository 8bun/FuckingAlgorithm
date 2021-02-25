package 寻找旋转排序数组中的最小值II;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/15 14:13
 * @Description: 注意数组中可能存在重复的元素,例如 1,2,2,2,0,0
 * @Url: https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * @限制:
 * @Level:
 */
public class Solution {

    public int findMin(int[] nums) {
        return binarySearch(nums,0,nums.length-1);
    }

    private int binarySearch(int[] nums, int left,int right) {
        if(left==right)
            return nums[left];
        else {
            int mid=(left+right)>>1;
            if(nums[mid]>nums[right]) //1,2,2,2,0,0
                return binarySearch(nums,mid+1,right);
            else if(nums[mid]<nums[right]) //3,1,2
                return binarySearch(nums,left,mid);
            else //2,0,0
                return binarySearch(nums,left,right-1);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
