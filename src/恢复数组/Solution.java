package 恢复数组;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/19 09:57
 * @Description:
 * @Url: https://leetcode-cn.com/problems/restore-the-array/
 * @限制:
 * @Level:
 */
public class Solution {

    private static final int mod=1000000007;
    public int numberOfArrays(String s, int k) {
        long[] dp=new long[s.length()+1];
        dp[s.length()]=1; //dp[i]表示从a[i]到a[len-1]的字符串符合要求的方案数
        for(int i=s.length()-1;i>=0;i--){ //从末尾开始遍历
            //前导零
            if(s.charAt(i)=='0'){  //如果遇到0,因为a[i]到a[len-1]必须要分割出的数字属于[1,k]，也就是不可能是0
                //而且题目要求不能带有前导0，所以dp[i]=0，就是没有方案数
                dp[i]=0;
                continue;
            }
            //不是0的情况
            //解析分割出的第一个整数的值
            long parseRes=0;
            //从位置i开始向后遍历
            for(int j=i;j<s.length();j++){
                parseRes=parseRes*10+s.charAt(j)-'0'; //记录a[i]到a[j]构成的数字
                if(parseRes<=k)  //符合要求，那么a[i]到a[j]可以组成一组，然后再加上不包含a[i]到a[j]字符串的a[j+1]的dp[j+1]
                    dp[i]=(dp[i]+dp[j+1]) % mod;
                else //当这个整数大于 k 时
                    break;
            }
        }
        return (int)dp[0];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
