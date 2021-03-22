package 删掉一个元素以后全为1的最长子数组;

import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/6/27 22:42
 * @Description:
 * @link
 * @限制:
 * @Level:
 */
public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] n = {1, 1, 0, 1};
        int r = new Solution().longestSubarray(n);
        System.out.println(r);
    }

    public int longestSubarray(int[] nums) {
        StringBuilder s = new StringBuilder();
        for (int n : nums) s.append(n);
        System.out.println(s);
        String[] arr = s.toString().split("0");
        if (arr.length == 1) {
            if (arr[0].contains("1") && ! s.toString().contains("0")) return arr[0].length() - 1;
            else if (arr[0].contains("1") && s.toString().contains("0")) return arr[0].length();
            else return 0;
        }
        int l = 0, r = 1;
        int res = 0;
        for (; r < arr.length; r++) {
            l = r - 1;
            res = Math.max(res, arr[l].length() + arr[r].length());
        }
        return res;
    }
}
