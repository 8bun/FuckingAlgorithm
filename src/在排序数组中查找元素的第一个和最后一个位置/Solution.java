package 在排序数组中查找元素的第一个和最后一个位置;

/**
 * @author cwq
 * @link https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/yi-wen-dai-ni-gao-ding-er-fen-cha-zhao-j-ymwl/
 * @since 2021/03/20
 */

public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int right = rightBound(nums, target);
        int left = leftBound(nums, target);
        if (right < left) {
            return new int[]{- 1, - 1};
        }
        return new int[]{left, right};
    }

    private int leftBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (target <= nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            }
        }
        return left;
    }

    private int rightBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (target >= nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            }
        }
        return right;
    }

}
