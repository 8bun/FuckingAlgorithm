package 和为K的最少斐波那契数字数目;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/18 22:47
 * @Description:
 * 任意一个正整数都可以拆分为若干个斐波纳契数
 * @Url: https://leetcode-cn.com/contest/biweekly-contest-24/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/
 * @限制:
 * @Level:
 */
public class Solution {

    public int findMinFibonacciNumbers(int k) {
        int[] fib=new int[50];
        fib[0]=1;
        fib[1]=1;
        int i=2;
        for (;fib[i-1]<k;i++)
            fib[i]=fib[i-1]+fib[i-2];
        int index=i-1;
        if (fib[index]==k)
            return 1;
        else
            return 1+findMinFibonacciNumbers(k-fib[index-1]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int s=new Solution().findMinFibonacciNumbers(20);
        System.out.println(s);
    }
}
