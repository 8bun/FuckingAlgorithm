package 找出数组中的幸运数;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/6 23:06
 * @Description:
 * @Url: https://leetcode-cn.com/contest/weekly-contest-182/problems/find-lucky-integer-in-an-array/
 * @限制:
 * @Level:
 */
public class Solution {

    public int findLucky(int[] arr) {
        int res=-1;
        int[] nums=new int[501];
        Arrays.fill(nums,0);
        for (int num:arr)
            nums[num]++;
        for (int i=0;i<501;i++){
            if (nums[i]!=0&&nums[i]==i)
                res=i;
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
