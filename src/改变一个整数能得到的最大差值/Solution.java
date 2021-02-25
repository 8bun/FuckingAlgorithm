package 改变一个整数能得到的最大差值;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/2 23:13
 * @Description:
 * @Url: https://leetcode-cn.com/contest/biweekly-contest-25/problems/max-difference-you-can-get-from-changing-an-integer/
 * @限制:
 * @Level:
 */
public class Solution {
    public int maxDiff(int num) {
        if (num>=1&&num<=9)
            return 8;
        String strNum=String.valueOf(num);
        char firstNum=strNum.charAt(0);
        int min,max = 0;
        if (firstNum=='1'){
            int index=1;
            while (index<strNum.length()&&(strNum.charAt(index++)<='1'));
            if (index==strNum.length())
                min=num;
            else {
                min = change(num, strNum.charAt(index - 1), '0');
            }
            max=change(num,firstNum,'9');
        }
        else {
            if (firstNum=='9'){
                int index=1;
                while (index<strNum.length()&&strNum.charAt(index++)=='9');
                if (index==strNum.length())
                    max=num;
                else max=change(num,strNum.charAt(index-1),'9');
            }
            else max=change(num,firstNum,'9');
            min=change(num,firstNum,'1');
        }
        System.out.println(max);
        System.out.println(min);
        return max-min;
    }
    private int change(int num,char replace,char to){
        String str=String.valueOf(num);
        StringBuilder newstr= new StringBuilder();
        for (char ch:str.toCharArray()){
            if (ch==replace){
                newstr.append(to);
                continue;
            }
            newstr.append(ch);
        }
        return Integer.parseInt(newstr.toString());
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
