package 两数之和之输入有序数组;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/18 10:15
 * @Description:
 * @Url: https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 * @限制:
 * @Level:
 */
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        return twoSum(numbers, target,0,numbers.length-1);
    }

    public int[] twoSum(int[] numbers, int target,int left,int right) {
        int sum=numbers[left]+numbers[right];
        if(sum==target){
            int[] res=new int[2];
            res[0]=left+1;res[1]=right+1;
            return res;
        }
        else if(sum>target)
            return twoSum(numbers,target,left,right-1);
        else
            return twoSum(numbers, target, left+1, right);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
