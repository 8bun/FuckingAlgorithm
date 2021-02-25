package 数组中出现次数超过一半的数字;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @description: 对数组进行排序，因为某个数字出现的个数超过了数组的一半，那么这个数字在排序数组中无论在哪里开始结束，一定会经过排序数组的中心
 * @url: https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/
 * 限制：1 <= 数组长度 <= 50000
 * @author: Jack
 * @createTime: 2020/3/1 23:18
 * @level: 简单
 */
public class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}
