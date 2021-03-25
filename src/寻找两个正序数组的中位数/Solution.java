package 寻找两个正序数组的中位数;

/**
 *
 * @author cwq
 * @since 2021/03/23
 * @link https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * https://www.cnblogs.com/grandyang/p/4465932.html
 */

public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, left = (m + n + 1) / 2, right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    /**
     * 从数组nums1的nums1[i]和数组nums2的nums2[j]开始的合并子数组中找第k个元素
     */
    private double findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        // i看成空的
        if(i >= nums1.length) {
            return nums2[j + k - 1];
        }
        if(j >= nums2.length) {
            return nums1[i + k - 1];
        }
        if(k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        // 从nums[i]开始的右边的第k/2个元素
        /**
         * 为什么是最大值：
         * 比如 nums1 = {3}，nums2 = {2, 4, 5, 6, 7}，K=4，要找两个数组混合中第4个数字，则分别在 nums1 和 nums2 中找第2个数字，
         * 而 nums1 中只有一个数字，不存在第二个数字，则 nums2 中的前2个数字可以直接跳过，
         * 为啥呢，因为要求的是整个混合数组的第4个数字，不管 nums1 中的那个数字是大是小，
         * 第4个数字绝不会出现在 nums2 的前两个数字中，所以可以直接跳过。
         */
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        /**
         * 比如 nums1 = {1, 3}，nums2 = {2, 4, 5}，K=4，要找两个数组混合中第4个数字，那么分别在 nums1 和 nums2 中找第2个数字，
         * nums1 中的第2个数字是3，nums2 中的第2个数字是4，由于3小于4，
         * 所以混合数组中第4个数字肯定在 nums2 中，可以将 nums1 的起始位置向后移动 K/2 个。
         * 反之，淘汰 nums2 中的前 K/2 个数字，并将 nums2 的起始位置向后移动 K/2 个，并且此时的K也自减去 K/2，调用递归即可
         * 这里不能直接写成k/2，因为k - k / 2不一定等于k/2
         */
        if(midVal1 < midVal2) {
            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        }
        else {
            return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }
}
