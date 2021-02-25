package 寻找旋转排序数组中的最小值;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/15 13:59
 * @Description:
 * @Url: https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/submissions/
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
            if(nums[mid]>nums[right]) //3 4 5 1 2
                return binarySearch(nums,mid+1,right);
            else //4 1 2 3
                return binarySearch(nums,left,mid);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nums[]={4,5,6,7,0,1,2};
        int res=new Solution().findMin(nums);
        System.out.println(res);
    }
}
