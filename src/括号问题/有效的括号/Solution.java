package 括号问题.有效的括号;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author cwq
 * @since 2021/03/24
 * @link https://leetcode-cn.com/problems/valid-parentheses/solution/
 */

public class Solution {
    public boolean isValid(String s) {
        int len = s.length();
        if(len == 0) {
            return true;
        }
        if(len % 2 != 0) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(')', '(');
        map.put(']', '[');
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if(ch == '(' || ch == '{' || ch == '['){
                st.add(ch);
            }
            else{

                if(!st.isEmpty() && st.peek().equals(map.get(ch))) {
                    st.pop();
                }
                else {
                    return false;
                }
            }
        }
        return st.isEmpty();
    }
}
