package 括号问题.有效的括号字符串;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 双栈解法：
 * 两个栈分别将"("和"*"的序号压入栈中，每次遇到右括号，首先检测左括号栈中是否为空，不为空则弹出元素，
 * 否则弹出star栈，最后考虑left和star栈可能存在元素，判断此时栈中元素的序号大小，
 * 如果left栈中的序号大于star中的则表明 存在"*("此种情况，直接false
 * @author cwq
 * @since 2021/03/17
 * @link https://leetcode-cn.com/problems/valid-parenthesis-string/
 */
public class Solution {

    public boolean checkValidString(String s) {
        Deque<Integer> left = new ArrayDeque<>();
        Deque<Integer> star = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                left.offerLast(i);
            }
            else if(chars[i] == '*') {
                star.offerLast(i);
            }
            else {
                // 优先使用'('来抵消')'
                if(!left.isEmpty()) {
                    left.removeLast();
                }
                else if(!star.isEmpty()) {
                    star.removeLast();
                }
                else {
                    return false;
                }
            }
        }
        // 前面')'都被抵消完了，剩下的')'和'*'要进行配对，需要注意下标的大小
        while (!left.isEmpty() && !star.isEmpty()) {
            // 出栈-比较
            //如果出现*(，则不能构成()
            if(star.removeLast() < left.removeLast()) {
                return false;
            }
        }
        // 前面循环的退出条件是一者为空或者两者均空，star栈最后空不空无所谓，因为它可以匹配空字符
        return left.isEmpty();
    }
}
