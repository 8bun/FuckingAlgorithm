package 编码方法;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/19 23:02
 * @Description:
 * 动态规划
 * @Url: https://leetcode-cn.com/problems/decode-ways/
 * @限制:
 * @Level:
 */
public class Solution {

    public int numDecodings(String s) {
        int len=s.length();
        if (len==0||s.charAt(0)=='0')
            return 0;
        int[] dp=new int[len];
        dp[0]=1;
        if(len>1){
            int num=(s.charAt(0) - '0') * 10 + (s.charAt(1) - '0');
            if (num%10==0&&num/10>2)  //30,40...
                return 0;
            if (num > 26||num%10==0) { //27,20,10...
                dp[1]=1;
            }
            else dp[1]=2; //12,24..
        }
        boolean isValid=true;
        for (int i=2;i<len;i++){
            int num_at_i=s.charAt(i)-'0';
            int nums_at_i_and_before=(s.charAt(i-1)-'0')*10+num_at_i;
            if(num_at_i==0&&(nums_at_i_and_before/10>2||nums_at_i_and_before==0)){//40||00
                isValid=false;break;
            }
            //自己结合的情况例如，26,01
            if (nums_at_i_and_before>26||nums_at_i_and_before==num_at_i) dp[i]=dp[i-1];
            //一定要和前面结合的情况，10,20
            else if(num_at_i==0)
                dp[i]=dp[i-2];
            else//两种结合选择,12,24..
                dp[i]=dp[i-1]+dp[i-2];
        }
        return isValid?dp[len-1]:0;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int s=new Solution().numDecodings("110");
        System.out.println(s);
    }
}
