package 字符串相乘;

/**
 * @description：
 * @url： https://leetcode-cn.com/problems/multiply-strings/
 * @限制：
 * @author：Jack
 * @createTime：2020/4/12 9:58
 * @level：
 */
public class Solution {

    public String multiply(String num1, String num2) {

        if ("0".equals(num1) || "0".equals(num2))
            return "0";
        int len1 = num1.length();
        int len2 = num2.length();

        int[] res = new int[len1 + len2];  //存储结果位的结果数组
        for (int i = len2 - 1; i >= 0; i--) {
            for (int j = len1 - 1; j >= 0; j--) {
                //先不管进位
                res[i + j + 1] += (num1.charAt(j) - '0') * (num2.charAt(i) - '0');
            }
        }

        int carry = 0;
        //处理进位
        //72变2，记录进位7给下一位加,/10就可以得到进位,%10可以得到进位后的结果
        for (int i = res.length - 1; i >= 0; i--) {
            int tmp = res[i] + carry;
            int r = tmp % 10;  //加上进位后的结果
            carry = tmp / 10; //给前面的位加的进位
            res[i] = r;  //更新
        }

        //将结果转化为字符串，使用Stringbuffer
        StringBuilder builder = new StringBuilder();
        for (int i : res) {
            builder.append(i);
        }
        //去掉0，结果最多只会出现首位是0的情况
        String ans = builder.toString();
        return ans.charAt(0) == '0' ? ans.substring(1) : ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().multiply("1", "0"));
    }
}
