package 计算右侧小于当前元素的个数;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * @Author: Jack
 * @Date: 2020/7/11 10:08
 * @Description: 
 * @Url: https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/
 * @限制: 
 * @Level: 
 */
public class Solution {

    //暴力超时
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int cnt = 0;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[i]) cnt++;
            }
            res.add(cnt);
        }
        return res;
    }
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
    }
}
