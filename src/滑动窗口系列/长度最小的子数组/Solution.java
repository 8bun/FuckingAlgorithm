package 滑动窗口系列.长度最小的子数组;

/**
 * 滑动窗口 https://grandyang.com/leetcode/209/
 * 这道题给定了我们一个数字，让求子数组之和大于等于给定值的最小长度，注意这里是大于等于，不是等于。跟之前那道 Maximum Subarray 有些类似，
 * 并且题目中要求实现 O(n) 和 O(nlgn) 两种解法，那么先来看 O(n) 的解法，需要定义两个指针 left 和 right，分别记录子数组的左右的边界位置，
 * 然后让 right 向右移，直到子数组和大于等于给定值或者 right 达到数组末尾，此时更新最短距离，
 * 并且将 left 像右移一位，然后再 sum 中减去移去的值，然后重复上面的步骤，直到 right 到达末尾，且 left 到达临界位置，即要么到达边界，要么再往右移动，
 * 和就会小于给定值。
 */
public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int left = 0, right = 0, sum = 0, len = nums.length, res = len + 1;
        while (right < len) {
            // 让 right 向右移，直到子数组和大于等于给定值或者 right 达到数组末尾
            while (sum < target && right < len) {
                sum += nums[right++];
            }
            
            // 此时更新最短距离，并且将 left 像右移一位，然后再 sum 中减去移去的值，然后重复上面的步骤
            while (sum >= target) {
                res = Math.min(res, right - left);
                sum -= nums[left++];
            }
        }
        return res == len + 1 ? 0 : res;
    }
}
