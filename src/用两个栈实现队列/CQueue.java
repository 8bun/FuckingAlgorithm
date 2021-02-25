package 用两个栈实现队列;

import java.util.Stack;

/**
 * @description:
 * @url:https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * @author:Jack
 * @createTime:2020/2/21 14:46
 * @level:简单
 */
public class CQueue {

    private Stack<Integer> stackA;
    private Stack<Integer> stackB;

    public CQueue() {
        stackA=new Stack<>();
        stackB=new Stack<>();
    }
    //往A中加入元素
    public void appendTail(int value) {
        stackA.push(value);
    }

    /**
     * 栈A输入，输入到栈B后输出
     * 删除时，分两种情况：
     * 栈B非空，直接出栈，
     * 栈B空，如果A非空，A出栈到B，B再出栈
     * @return
     */
    public int deleteHead() {
        if(!stackB.isEmpty())
            return stackB.pop();
        else{
            while (!stackA.isEmpty()){
                stackB.push(stackA.pop());
            }
        }
        if(!stackB.isEmpty())
            return stackB.pop();
        else
            return -1;
    }
}
