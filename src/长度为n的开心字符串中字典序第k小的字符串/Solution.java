package 长度为n的开心字符串中字典序第k小的字符串;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/18 23:59
 * @Description: 没有回溯的回溯法
 * @Url: https://leetcode-cn.com/contest/biweekly-contest-24/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/
 * @限制:
 * @Level:
 */
public class Solution {

    public String getHappyString(int n, int k) {
        List<String> res=new ArrayList<>();
        backtrace("a",n-1,res);
        backtrace("b",n-1,res);
        backtrace("c",n-1,res);
        if (res.size()<k)
            return "";
        Collections.sort(res);
        return res.get(k-1);
    }

    private void backtrace(String cur_str, int cur_left_Str_len, List<String> res) {
        //长度已经达到要求，或者后两个重复
        if(cur_left_Str_len<0||cur_str.length()>1&&cur_str.charAt(cur_str.length()-1)==cur_str.charAt(cur_str.length()-2))
            return;
        if (cur_left_Str_len==0) {
            res.add(cur_str);
            return;
        }
        backtrace(cur_str+"a",cur_left_Str_len-1,res);
        backtrace(cur_str+"b",cur_left_Str_len-1,res);
        backtrace(cur_str+"c",cur_left_Str_len-1,res);
    }

    public static void main(String[] args) {
        new Solution().getHappyString(1,3);
        Scanner scanner = new Scanner(System.in);
    }
}
