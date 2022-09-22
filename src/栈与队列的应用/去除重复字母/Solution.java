package 栈与队列的应用.去除重复字母;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/remove-duplicate-letters/solution/you-qian-ru-shen-dan-diao-zhan-si-lu-qu-chu-zhong-/
 * 1.如何保证去重
 * 2.如何保证字符相对位置不变：按顺序压栈即可
 * 3.pop出来的元素会不会永远丢失，如果没遍历到的字母还有这个pop出来的元素，就可以放心pop，靠后面再去（通过一个count数组，如果遍历过这个字母，就-1）
 * 4.栈内的元素是不重复的
 */
public class Solution {
    public String removeDuplicateLetters(String s){
        int n = s.length();
        Stack<Character> stack = new Stack<>();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i)-'a']++;
        }

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            count[ch - 'a']--;
            // 结果不能重复
            if (stack.contains(ch)) {
                continue;
            }
            if (stack.isEmpty()) {
                stack.add(ch);
            } else {
                // 如果栈顶元素比当前字符还要大，并且后续还有，果断pop
                while (!stack.isEmpty() && stack.peek() > ch && count[stack.peek() - 'a'] > 0) {
                    stack.pop();
                }
                stack.add(ch);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}



