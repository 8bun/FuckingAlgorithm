package 最接近的三数之和;

import java.util.Arrays;

/**
 * 这道题让我们求最接近给定值的三数之和，是在之前那道 3Sum 的基础上又增加了些许难度，
 * 那么这道题让返回这个最接近于给定值的值，即要保证当前三数和跟给定值之间的差的绝对值最小，
 * 所以需要定义一个变量 diff 用来记录差的绝对值，
 * 然后还是要先将数组排个序，然后开始遍历数组，思路跟那道三数之和很相似，
 * 都是先确定一个数，然后用两个指针 left 和 right 来滑动寻找另外两个数，每确定两个数，
 * 求出此三数之和，然后算和给定值的差的绝对值存在 newDiff 中，然后和 diff 比较并更新 diff 和结果 closest 即可
 * https://www.cnblogs.com/grandyang/p/4510984.html
 * @author cwq
 * @since 2021/03/25
 * @link https://leetcode-cn.com/problems/3sum-closest/
 */

public class Solution {

    public int threeSumClosest(int[] nums, int target) {
        int closest = nums[0] + nums[1] + nums[2];
        int diff = Math.abs(closest - target);
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if(nums[i] * 3 > target) {
                return Math.min(closest, nums[i] + nums[i + 1] + nums[i + 2]);
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int newDiff = Math.abs(sum - target);
                if(newDiff < diff) {
                    diff = newDiff;
                    closest = sum;
                }
                // 这样移动使sum更接近于target
                if(sum < target) {
                    left++;
                }
                else {
                    right--;
                }
            }
        }
        return closest;
    }
}
