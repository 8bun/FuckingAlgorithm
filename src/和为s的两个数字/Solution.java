package 和为s的两个数字;

/**
 * @description：
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，
 * 则输出任意一对即可。
 * 初始化： 双指针 ii , jj 分别指向数组 numsnums 的左右两端 （俗称对撞双指针）。
 * 循环搜索： 当双指针相遇时跳出；
 * 计算和 s = nums[i] + nums[j]s=nums[i]+nums[j] ；
 * 若 s > targets>target ，则指针 jj 向左移动，即执行 j = j - 1j=j−1 ；
 * 若 s < targets<target ，则指针 ii 向右移动，即执行 i = i + 1i=i+1 ；
 * 若 s = targets=target ，立即返回数组 [nums[i], nums[j]][nums[i],nums[j]] ；
 * @url： https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof/
 * @限制：
 * @author：Jack
 * @createTime：2020/4/2 23:04
 * @level：
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        return search(nums,target,0,nums.length-1);
    }
    private int[] search(int[] nums,int target,int left,int right){
        if(left<right) {
            int sum = nums[left] + nums[right];
            if(sum<target){
                return search(nums,target,left+1,right);
            }
            else if(sum>target){
                return search(nums,target,left,right-1);
            }else {
                return new int[]{nums[left],nums[right]};
            }
        }
        else
            return new int[]{};
    }
}
