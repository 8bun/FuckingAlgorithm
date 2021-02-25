package 数组中重复的数字;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 * HashSet本身不保存重复元素
 */
class Solution {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> numsSet=new HashSet<>();
        int n=nums.length;
        for(int num:nums){
           if(!numsSet.add(num))
               return num;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a={1,2,3,4,5,6};
        System.out.println(new Solution().findRepeatNumber(a));
    }
}