package 栈与队列的应用.移掉K位数字;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/remove-k-digits/solution/yi-diao-kwei-shu-zi-by-leetcode-solution/
 */
public class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        if (num.length() <= k) {
            return "0";
        }
        for (int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
            if (stack.isEmpty()) {
                stack.add(ch);
            }
            else {
                if (k <= 0) {
                    stack.add(ch);
                }
                else {
                    while (k > 0 && !stack.isEmpty() && stack.peek() > ch) {
                        stack.pop();
                        k--;
                    }
                    stack.add(ch);
                }
            }
        }
        // 防止112 1这样的case，此时stack为112, k=1
        while (!stack.isEmpty() && k > 0) {
            // 从大的开始删
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        // 去掉前导0
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.toString().equals("") ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        String num = "112";
        System.out.println(new Solution().removeKdigits(num, 2));
    }
}
