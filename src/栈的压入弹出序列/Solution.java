package 栈的压入弹出序列;

import java.util.Stack;

/**
 * @description:尝试按照 popped 中的顺序模拟一下出栈操作，如果符合则返回 true，否则返回 false。这里到的贪心法则是如果栈顶元素等于 popped 序列中下一个要 pop 的值，则应立刻将该值 pop 出来。
 * @url:https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
 * @author:Jack
 * @createTime:2020/2/27 21:52
 * @level:
 */
public class Solution {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack=new Stack<>();  //该栈模拟操作
        int index_pop=0;  //出栈元素下标
        for(int i=0;i<pushed.length;i++){
            stack.push(pushed[i]);
            //循环到栈顶不等于下一个出栈的元素才继续压栈
            //非空才可压栈并且index_pop不可越界
            while (!stack.isEmpty()&&index_pop<popped.length&&stack.peek()==popped[index_pop]){
                stack.pop();
                index_pop++;
            }
        }
        return stack.isEmpty();
    }
}
