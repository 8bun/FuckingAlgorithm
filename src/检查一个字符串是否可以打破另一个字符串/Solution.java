package 检查一个字符串是否可以打破另一个字符串;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/3 00:09
 * @Description:
 * @Url: https://leetcode-cn.com/contest/biweekly-contest-25/problems/check-if-a-string-can-break-another-string/
 * @限制:
 * @Level:
 */
public class Solution {

    public boolean checkIfCanBreak(String s1, String s2) {
        if (s1.length()!=s2.length())
            return false;
        char[] chars1=s1.toCharArray();
        Arrays.sort(chars1);
        s1=new String(chars1);
        char[] chars2=s2.toCharArray();
        Arrays.sort(chars2);
        s2=new String(chars2);
        int len=s1.length();
        boolean r1=false,r2=false;
        if (s1.charAt(0)>=s2.charAt(0)){
            int index=1;
            while (index<len&&s1.charAt(index)>=s2.charAt(index)){
                index++;
            }
            if (index==len)
                r1=true;
        }
        if (!r1 && s1.charAt(0)<=s2.charAt(0)){
            int index=1;
            while (index<len&&s1.charAt(index)<=s2.charAt(index)){
                index++;
            }
            if (index==len)
                r2=true;
        }
        return r1||r2;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new Solution().checkIfCanBreak("abc","xya");
    }
}
