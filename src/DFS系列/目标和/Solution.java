package DFS系列.目标和;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/5 21:16
 * @Description:
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。
 * 现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * 这道题采用dfs属于暴力做法
 * @Url: https://leetcode-cn.com/problems/target-sum/
 * @限制:
 * @Level:
 */
public class Solution {
    private int sum=0;
    public int findTargetSumWays(int[] nums, int S) {
        dfs(nums,0,S,0);
        return sum;
    }

    /**
     *
     * @param nums
     * @param curIndex
     * @param target
     * @param curSum
     */
    private void dfs(int[] nums,int curIndex,int target,int curSum){
        if (curIndex==nums.length){
            if (curSum==target)
                sum++;
            return;
        }
        //两种搜索路径选择
        dfs(nums,curIndex+1,target,curSum+nums[curIndex]);
        dfs(nums,curIndex+1,target,curSum-nums[curIndex]);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
