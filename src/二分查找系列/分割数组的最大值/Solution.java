package 二分查找系列.分割数组的最大值;

/**
 * 求 m 个子数组各自和的最大值最小。
 * https://www.cnblogs.com/grandyang/p/5933787.html
 * @author cwq
 * @since 2021/03/23
 * @link https://leetcode-cn.com/problems/split-array-largest-sum/
 */

public class Solution {
    public int splitArray(int[] nums, int m) {
        long leftSum = 0, rightSum = 0;
        for (int i = 0; i < nums.length; i++) {
            leftSum = Math.max(leftSum, nums[i]);
            rightSum += nums[i];
        }
        while (leftSum < rightSum) {
            long midSum = leftSum + (rightSum - leftSum) / 2;
            if(canSplit(nums, m, midSum)) {
                rightSum = midSum;
            }
            else {
                leftSum = midSum + 1;
            }
        }
        return (int) rightSum;
    }

    /**
     * 判断数组nums能否分成m个子数组，并且每个子数组的和都<=sum
     */
    private boolean canSplit(int[] nums, int m, long sum) {
        long cnt = 1, curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            if(curSum > sum) {
                curSum = nums[i];
                ++cnt;
                if (cnt > m) {
                    return false;
                }
            }
        }
        return true;
    }
}
