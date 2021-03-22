package 栈与队列的应用.字符串解码;

import java.util.LinkedList;

/**
 * 根据题目，"a3a[a]2[bc]"是非法的，'['前面必会出现数字
 * 本题核心思路是在栈里面每次存储两个信息, (左括号前的字符串, 左括号前的数字),
 * 比如abc3[def], 当遇到第一个左括号的时候，压入栈中的是("abc", 3),（可利用双栈来保存）
 * 然后遍历括号里面的字符串def, 当遇到右括号的时候, 从栈里面弹出一个元素(s1, n1),
 * 得到新的字符串为s1+n1*"def", 也就是abcdefdefdef。对于括号里面嵌套的情况也是同样处理方式。
 * 凡是遇到左括号就进行压栈处理，遇到右括号就弹出栈，栈中记录的元素很重要。
 * @author cwq
 * @since 2021/03/16
 * @link https://leetcode-cn.com/problems/decode-string/
 */
public class Solution {
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        // 保存数字
        LinkedList<Integer> numStack = new LinkedList<>();
        // 保存当前字符串
        LinkedList<String> strStack = new LinkedList<>();
        for(Character c : s.toCharArray()) {
            // '[', 入栈左边的统计
            if(c == '[') {
                // 先保存'['之前的情况
                numStack.addLast(multi);
                strStack.addLast(res.toString());
                multi = 0;
                // 重置直到遇到']'，从栈中计算结果，赋值给res
                res = new StringBuilder();
            }

            else if(c == ']') {
                StringBuilder tmp = new StringBuilder();
                // 获取该']'最近的左边的'['左边统计的乘数
                int curMulti = numStack.removeLast();
                for(int i = 0; i < curMulti; i++) {
                    tmp.append(res);
                }
                res = new StringBuilder(strStack.removeLast() + tmp);
            }
            else if(c >= '0' && c <= '9') {
                // 这里也就解释了为什么一开始multi初始化为0
                multi = multi * 10 + Integer.parseInt(c + "");
            }
            else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
