package 二的幂;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/19 22:54
 * @Description: 该数的二进制一定只有一个1
 * @Url: https://leetcode-cn.com/problems/power-of-two/
 * @限制:
 * @Level:
 */
public class Solution {

    public boolean isPowerOfTwo(int n) {
        return (n>0)&&(n&(n-1))==0;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
