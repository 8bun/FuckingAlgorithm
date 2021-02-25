package 调整数组顺序使奇数位于偶数前面;

/**
 * @description:首尾双指针和快慢双指针可以解决问题，具体看https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/solution/ti-jie-shou-wei-shuang-zhi-zhen-kuai-man-shuang-zh/
 * @url:https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 * @author:Jack
 * @createTime:2020/2/25 19:17
 * @level:简单
 */
public class Solution {

    private int[] nums;
    public int[] exchange(int[] nums) {
        this.nums=nums;
        int left=0;
        int right=nums.length-1;
        while (left<right){
            if(nums[left]%2!=0){
                left++;
                continue;
            }
            if(nums[right]%2==0){
                right--;
                continue;
            }
            swap(left,right);
        }
        return nums;
    }
    private void swap(int left,int right){
        int tmp=nums[left];
        nums[left]=nums[right];
        nums[right]=tmp;
    }
}
