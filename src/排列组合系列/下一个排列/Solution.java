package 排列组合系列.下一个排列;

/**
 * 规律查找
 * 将给定数字序列重新排列成字典序中下一个更大的排列。如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 * @author cwq
 * @since 2021/03/26
 * @link https://leetcode-cn.com/problems/next-permutation/solution/
 * https://www.cnblogs.com/grandyang/p/4428207.html
 */

public class Solution {
    public void nextPermutation(int[] nums) {
        int i, j, n = nums.length;
        for (i = n - 2; i >= 0; -- i) {
            // 如果从后往前递减，那么从后面的数从后往前找到第一个比num[i]大的数字
            if (nums[i + 1] > nums[i]) {
                for (j = n - 1; j > i; -- j) {
                    if (nums[j] > nums[i]) {
                        break;
                    }
                }
                // 交换
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                // 转置
                reverse(nums, i + 1, n - 1);
                return;
            }
        }
        reverse(nums, 0, n - 1);
    }
    private void reverse(int[] num, int i, int j) {
        for (int k = i; k <= (j + i) / 2; k++) {
            int tmp = num[k];
            num[k] = num[j + i - k];
            num[j + i - k] = tmp;
        }
    }
}
