package 贪心系列.整数转罗马数字;

/**
 * @author cwq
 * @since 2021/03/23
 * @link https://leetcode-cn.com/problems/integer-to-roman
 * https://leetcode-cn.com/problems/integer-to-roman/solution/tan-xin-suan-fa-by-liweiwei1419/
 */

public class Solution {
    public static void main(String[] args) {
        String s = new Solution().intToRoman(1489);
        System.out.println(s);
    }
    public String intToRoman(int num) {
        String res = "";
        int[] val = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] str = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        /**
         * 例如：1489=
         * "M" + 489
         * ="M" + "CD" + 89
         * ="M" + "CD" + "L" + 39
         * ="M" + "CD" + "L" + "X" + "X" + "X" + 9
         * ="M" + "CD" + "L" + "X" + "X" + "X" + "IX"
         */
        for (int i = 0; i < val.length; i++) {
            while (num >= val[i]) {
                num -= val[i];
                res += str[i];
            }
        }
        return res;
    }
}
