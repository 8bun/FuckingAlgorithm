package 最大数;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/16 00:21
 * @Description: 自定义排序
 * @Url: https://leetcode-cn.com/problems/largest-number/comments/
 * @限制:
 * @Level:
 */
public class Solution {

    public String largestNumber(int[] nums) {
        String[] strNums=new String[nums.length];
        int index=0;
        for (int num:nums)
            strNums[index++]=""+num;  //整数转化为字符串的一种方式
        //理解:https://blog.csdn.net/u013066244/article/details/78997869
        Arrays.sort(strNums,(o1, o2) -> (o2+o1).compareTo(o1+o2));
        StringBuilder builder=new StringBuilder();
        for (String str:strNums)
            builder.append(str);
        String res=builder.toString();
        return res.startsWith("0")?"0":res;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums={2,35,11};
        String res=new Solution().largestNumber(nums);
        System.out.println(res);
    }
}
