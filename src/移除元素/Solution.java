package 移除元素;

/**
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * @author cwq
 * @since 2021/03/25
 * @link https://leetcode-cn.com/problems/remove-element/
 */

public class Solution {
    public int removeElement(int[] nums, int val) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != val) {
                nums[res++] = nums[i];
            }
        }
        return res;
    }
}
