package 数组中的第K个最大元素;

/**
 * 不使用排序API，使用快速排序
 * @author cwq
 * @since 2021/03/24
 * @link https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 */

public class Solution {

    public int findKthLargest(int[] nums, int k) {
        return findK(nums, 0, nums.length - 1, k);
    }
    public int findK(int[] nums, int left, int right, int k) {
        if(left <= right) {
            int pivot = quickSort(nums, left, right);
            if(pivot == k - 1) {
                return nums[pivot];
            }
            else if(pivot < k - 1) {
                return findK(nums, pivot + 1, right, k);
            }
            else {
                return findK(nums, left, pivot - 1, k);
            }
        }
        return -1;
    }

    private int quickSort(int[] nums, int start, int end) {
        int pivot = nums[start];
        int left = start, right = end;
        while (left < right) {
            while (left < right && nums[right] <= pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] >= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

}
