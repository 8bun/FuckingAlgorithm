package 表示数值的字符串;

/**
 * @description:字符串"+100"、"5e2"、"-123"、"3.1416"、"0123"及"-1E-16"都表示数值
 * 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 * 从左到右逐步判断
 * @url:https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
 * @author:Jack
 * @createTime:2020/2/24 14:31
 * @level:中等
 */
public class Solution {

    private String str;
    private int cur_index;
    public boolean isNumber(String s) {
        this.str=s.trim();
        if(str.isEmpty())
            return  false;
        this.cur_index=0;
        boolean flag_dot=false; //是否出现过逗号
        boolean flag_e=false;  //是否出现过e
        boolean flag_num=false; //是否存在过数字
        char first=str.charAt(cur_index);
        if(first=='+'||first=='-'){
            cur_index++;
        }
        for(;cur_index<str.length();cur_index++){

            char c=str.charAt(cur_index);
            System.out.println(c);
            if(c>='0'&&c<='9'){
                flag_num=true;
            }
            else if(c=='.'){
                if(flag_e||flag_dot) //出现过e或.后出现了.，本题中，.1是数字
                    return false;
                flag_dot=true;
            }
            else if(c=='e'||c=='E'){
                if(flag_e||!flag_num||cur_index==s.length()-1)  //已存在e或没出现过数字或e处于末尾
                    return false;
                if(cur_index<str.length()-1) {
                    if (str.charAt(cur_index + 1) == '+' || str.charAt(cur_index + 1) == '-')
                        cur_index++;
                }
                flag_e=true;  //已存在e或E
                flag_num=false;  //后面必须为数字，先置为false
            }
            else return false;
        }
        return flag_num;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isNumber(" 005047e+6"));
    }
}
