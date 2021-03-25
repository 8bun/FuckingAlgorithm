package 删除有序数组中的重复项;

/**
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 这道题要我们从有序数组中去除重复项，和之前那道 Remove Duplicates from Sorted List 的题很类似，
 * 但是要简单一些，因为毕竟数组的值可以通过下标直接访问，
 * 而链表不行。那么这道题的解题思路是使用快慢指针来记录遍历的坐标，
 * 最开始时两个指针都指向第一个数字，如果两个指针指的数字相同，则快指针向前走一步，
 * 如果不同，则两个指针都向前走一步，这样当快指针走完整个数组后，慢指针当前的坐标加1就是数组中不同数字的个数
 * @author cwq
 * @since 2021/03/25
 * @link https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */

public class Solution {
    public int removeDuplicates(int[] nums) {
        int pre = 0, cur = 0, n = nums.length;
        while (cur < n) {
            if(nums[pre] == nums[cur]) {
                ++cur;
            }
            // 如果快指针和慢指针所指的元素不相同，证明慢指针所指元素的重复已结束，左索引pre++，并把新的元素赋给索引pre处
            // 也就是说如nums = {1,1,2,2,2,3,4,4,5}
            // 最后的结果是{1,2,3,4,5,...}因为题目给了一个print函数
            else {
                nums[++pre] = nums[cur++];
            }
        }
        return nums.length == 0 ? 0 : pre + 1;
    }
}
