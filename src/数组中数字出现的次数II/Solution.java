package 数组中数字出现的次数II;

import java.util.HashMap;

/**
 * @description：
 * 法一，采用HashMap存num：出现次数
 * @url： https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof
 * @限制：
 * @author：Jack
 * @createTime：2020/4/2 22:01
 * @level：
 */
public class Solution {

    public int singleNumber(int[] nums) {
        int len=nums.length;
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<len;i++){
            if(map.containsKey(nums[i])){
                int s=map.get(nums[i]);
                map.put(nums[i],s+1);
            }
            else {
                map.put(nums[i],1);
            }
        }
        int res=0;
        for (Integer num:map.keySet()){
            System.out.println("num"+num+" "+map.get(num));
            if(map.get(num)==1){
                res=num;break;
            }
        }
        return res;
    }
}
