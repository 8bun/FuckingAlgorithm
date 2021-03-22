package 用两个栈实现队列;

import java.util.Stack;

/**
 * @description:
 * @link  https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * @author   cwq
 * @createTime: 2020/2/21 14:46
 * @level: 简单
 */
public class CQueue {

    Stack<Integer> in;
    Stack<Integer> out;

    public CQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    /**
     * 往in中加入元素
     */
    public void appendTail(int value) {
        in.push(value);
    }

    /**
     * 栈in输入，输入到栈out后输出
     * 删除时，分两种情况：
     * 栈out非空，直接出栈，
     * 栈out空，如果in非空，in出栈到out，out再出栈
     */
    public int deleteHead() {
        if (! out.isEmpty()) {
            return out.pop();
        } else {
            if (! in.isEmpty()) {
                while (! in.isEmpty()) {
                    out.push(in.pop());
                }
            }
        }
        if (! out.isEmpty()) {
            return out.pop();
        } else {
            return - 1;
        }
    }
}
