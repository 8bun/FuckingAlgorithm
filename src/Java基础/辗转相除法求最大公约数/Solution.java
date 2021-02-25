package 基本问题.辗转相除法求最大公约数;

import java.util.Scanner;

/**
 * @description：计算两个非负整数 p 和 q 的最大公约数：若q=0，则最大公约数为p。
 * 否则，将 p 除以q 得到余数 r，p 和 q 的最大公约数即为 q 和r 的最大公约数
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/7 23:01
 * @level：
 */
public class Solution {
    public static void main(String[] args) {

        System.out.println(gcd(25,15));
        System.out.println(25*15/gcd(25,15));
    }
    private static int gcd(int a,int b){
        if(b==0)
            return a;
        return gcd(b,a%b);
    }
}
