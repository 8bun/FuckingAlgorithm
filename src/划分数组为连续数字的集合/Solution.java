package 划分数组为连续数字的集合;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/26 10:52
 * @Description:
 * @Url: https://leetcode-cn.com/contest/weekly-contest-168/problems/divide-array-in-sets-of-k-consecutive-numbers/
 * @限制:
 * @Level:
 */
public class Solution {

    public boolean isPossibleDivide(int[] nums, int k) {
        int len = nums.length;
        if(len % k != 0) return false;
        Arrays.sort(nums);

        PriorityQueue<Integer> list = new PriorityQueue<>(len);
        for (int n : nums){
            list.offer(n);
        }
        while (!list.isEmpty()){
            int f = list.poll();
            for(int i = 1; i < k; i++){
                if (!list.remove(f + i))  //如果移除失败
                    return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = {1,2,3,3,4,4,5,6};
        int k = 4;
        boolean res = new Solution().isPossibleDivide(nums, k);
        System.out.println(res);
    }
}
