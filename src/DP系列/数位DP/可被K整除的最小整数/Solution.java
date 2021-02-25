package DP系列.数位DP.可被K整除的最小整数;

import java.util.Scanner;
/**
 * @Author: Jack
 * @Date: 2020/6/10 16:48
 * @Description:
 * 本题不采用数位dp做法会好做很多：
 * 这里要解决的是如何防止a*10+1结果整数溢出的问题，
 * 设n=p*K+q
 * 则n*10+1=10*p*K+q*10+1;
 * 有n%K=q
 * 有(n*10+1)%K=(10*p*K+q*10+1)%K=(q*10+1)%K
 * 又((n%K)*10+1)%K=(q*10+1)%K
 * 推断出：(n*10+1)%K = ((n%K)*10+1)%K
 * @Url: https://leetcode-cn.com/problems/smallest-integer-divisible-by-k/
 * @限制: 1 <= K <= 10^5
 * @Level:
 */
public class Solution {

    public int smallestRepunitDivByK(int K) {
        if (K % 2 == 0 || K % 5 == 0) return -1;
        int len = 1;
        int num = 1;
        while (num % K != 0) {
            num = (num % K) * 10 + 1;
            len++;
        }
        return len;
    }

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
   }
}
