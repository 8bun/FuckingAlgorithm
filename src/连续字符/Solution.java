package 连续字符;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/16 22:30
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution {

    public int maxPower(String s) {
        int left = 0, right=0;
        int max = 0;
       for (;right<s.length(); right++){
           if (s.charAt(left)==s.charAt(right)) {
               max = Math.max(max, right - left +1);
           }
           else {
               left = right;
           }
       }
       return max;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int res = new Solution().maxPower("leetcode");
        System.out.println(res);
    }
}
