package DP系列.区间DP.戳气球;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/12 13:51
 * @Description:
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 
 * 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 * 求所能获得硬币的最大数量。
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      MaxCoins =  3*1*5    +  3*5*8    +    1*3*8    +  1*8*1  = 167
 * @Url: https://leetcode-cn.com/problems/burst-balloons/
 * @限制:
 * @Level:
 */
public class Solution {

    /**
     * 回溯超时，时间复杂度O(n!)
     */
    private int res=0;
    public int maxCoins(int[] nums) {
        LinkedList<Integer> list=new LinkedList<>();
        for (int num: nums)list.add(num);

        backtrace(list, 0);
        return res;
    }
    private void backtrace(LinkedList<Integer> nums,int curCoins){
        if (nums.size() == 0){
            res = Math.max(res, curCoins);return;
        }
        for (int i = 0; i < nums.size(); i++){
            int delta = nums.get(i) * (i - 1 < 0 ? 1 : nums.get(i-1)) * (i + 1 >= nums.size() ? 1 : nums.get(i+1));
            int tmp=nums.get(i);
            nums.remove(i);
            backtrace(nums, curCoins + delta);
            nums.add(i,tmp);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums={3,1,5,8};
        int res = new Solution().maxCoins(nums);
        System.out.println(res);
    }
}
