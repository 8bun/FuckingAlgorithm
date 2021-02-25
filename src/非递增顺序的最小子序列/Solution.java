package 非递增顺序的最小子序列;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/6 20:25
 * @Description:
 * @Url: https://leetcode-cn.com/contest/weekly-contest-183/problems/minimum-subsequence-in-non-increasing-order/
 * @限制:
 * @Level:
 */
public class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int all=getSum(nums,0);
        int i=nums.length-1;
        for (;i>=0;i--){
            if (2*getSum(nums,i)>all)
                break;
        }
        List<Integer> ans=new ArrayList<>();
        for (int j=nums.length-1;j>=i;j--)
            ans.add(nums[j]);
        return ans;
    }
    private int getSum(int[] nums,int start){
        int sum=0;
        for (int i=start;i<nums.length;i++) sum+=nums[i];
        return sum;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
