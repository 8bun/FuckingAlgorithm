package 将二进制表示减到1的步骤数;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/6 20:35
 * @Description:
 * 模拟，直到二进制表示中只有一个1
 * @Url: https://leetcode-cn.com/contest/weekly-contest-183/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
 * @限制:
 * @Level:
 */
public class Solution {

    int step=0;

    public int numSteps(String s) {
        int len=s.length();
        int sum=0;
        if (s.equals("1"))
            return 0;
        for (char ch:s.toCharArray())sum+=ch-'0';
        if (sum==1)
            return len-1+step;
        if (s.charAt(len-1)=='1'){
            if (sum==len){
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("1");
                for (int i=0;i<len;i++){
                    stringBuilder.append("0");
                }
                step++;
                return numSteps(stringBuilder.toString());
            }
            else {
                int flag=0;
                for (int i=len-1;i>=0;i--){
                    if (s.charAt(i)=='0') {
                        flag = i;
                        break;
                    }
                }
                StringBuilder stringBuilder=new StringBuilder();
                for (int i=flag+1;i<len;i++){
                    stringBuilder.append("0");
                }
                step++;
                return numSteps(s.substring(0,flag)+"1"+stringBuilder.toString());
            }
        }
        else{
            step++;
            return numSteps(s.substring(0,len-1));
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int res=new Solution().numSteps("10");
        System.out.println(res);
    }
}

