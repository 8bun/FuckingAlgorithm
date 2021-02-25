package 包含min函数的栈;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Stack;

/**
 * @description:定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
 * 调用 min、push 及 pop 的时间复杂度都是 O(1)。所以通过遍历查找最小元素是不可行的
 * 使用双栈可以解决，一个数据栈与正常工作，另一个为最小值栈，是一个自顶向下递增的栈
 * 也就是说每次数据栈要压数据，都会首先与最小值栈顶元素（最小值）比较，如果更小，那就也要
 * 压入最小值栈，如果大就不压；为了防止数据栈中的最小值被删除，所以删除时判断
 * 是否是最小值，是的话就在最小值栈也出栈了。
 * @url:https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/
 * @author:Jack
 * @createTime:2020/2/27 20:36
 * @level:简单
 */
public class MinStack {

    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;
    public MinStack() {
        dataStack=new Stack<>();
        minStack=new Stack<>();
        minStack.push(Integer.MAX_VALUE);  //为了使得第一个压入数据栈的元素一定能压入最小值栈
    }

    public void push(int x) {
        dataStack.push(x);
        if(x<=minStack.peek()){ //等于最小值也要入栈
            minStack.push(x);
        }
    }

    public void pop() {
        //注意此处不能写成dataStack.pop()==minStack.peek()，这样会导致出错
        int min = minStack.peek();
        if(dataStack.pop()==min){
            minStack.pop();
        }
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
