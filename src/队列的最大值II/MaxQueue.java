package 队列的最大值II;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @description：
 * 我们观察例如队列依次插入[1,2,4,3,1,7]后的最大值情况
 * 1,2,4,4,4,7
 * 我们定义一个队列维护最大值，元素num插入数值队列后最大值队列的头部一定是数值队列[...,num]的最大元素
 * 每次插入num前，要更新最大值队列，从队尾开始把小于它的值进行出队，然后将num加入最大值队列
 * @url：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/
 * @限制：
 * @author：Jack
 * @createTime：2020/4/3 10:37
 * @level：
 */
public class MaxQueue {

    private Deque<Integer> maxQueue=null;  //双端队列

    private Queue<Integer> queue=null;  //数值队列

    public MaxQueue() {
        maxQueue=new LinkedList<>();
        queue=new LinkedList<>();
    }

    public int max_value() {
        if(queue.isEmpty())
            return -1;
        else return maxQueue.peekFirst(); //最大值队列的队首
    }

    public void push_back(int value) {
        queue.offer(value);
        while (maxQueue.size()>0&&maxQueue.peekLast()<value){
            maxQueue.pollLast();
        }
        maxQueue.offerLast(value);
    }

    public int pop_front() {
        if(queue.isEmpty())
            return -1;
        else{
            int tmp=queue.poll();
            if(maxQueue.size()>0&&maxQueue.peekFirst()==tmp){//如果出队的元素是当前最大值，将deq的队首出队
                maxQueue.pollFirst();
            }
            return tmp;
        }
    }
}
