package 数据流中的中位数;

import java.util.PriorityQueue;

/**
 * @description：
 * 关于堆排序的介绍：https://www.cnblogs.com/lanhaicode/p/10546257.html
 * 堆排序过程：建堆→堆顶就是最大(或小)值，然后堆顶跟最后一个元素交换→调整堆，
 * 反复这个过程，直到堆里面所有元素都交换好；
 * 优先队列是一种数据结构，它是利用堆来实现的，可以利用优先级这个数据结构来描述某个问题，
 * 比如有一批不断输入的日期，我想要在任何时刻都能以O(1)的速度得到
 * 已经输入的日期中的最早日期，那么就可以用优先队列这个数据结构存储日期元素
 * 将中位数左右分为两个区间，对其进行排序得到大顶堆和小顶堆
 * 求中位数则根据两个堆的堆顶求即可
 * @url： https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/
 * @限制：
 * @author：Jack
 * @createTime：2020/3/2 11:41
 * @level：困难
 */
public class MedianFinder {
    /** initialize your data structure here. */
    //采用Lambda表达式写大顶堆，优先级队列默认是实现的小顶堆
    private PriorityQueue<Integer> left_queue;
    private PriorityQueue<Integer> right_queue;//小顶堆
    private int sum;  //添加之前的数据流的元素个数
    public MedianFinder() {
        left_queue=new PriorityQueue<>((o1, o2) -> (o2 - o1));
        right_queue=new PriorityQueue<>();
        sum=0;
    }

    /**
     * 为了保证左区间和右区间的元素差的绝对值<=1，当左右区间元素个数相同时，加入的新元素
     * 加入到左区间，不同时，加入元素少的一边即右区间。添加结束，调整左右的两个堆顶元素,使得整体还是
     * 升序的
     * @param num
     */
    public void addNum(int num) {
        if(sum%2==0){
            left_queue.add(num);
        }
        else {
            right_queue.add(num);
        }
        if(!left_queue.isEmpty() && !right_queue.isEmpty() &&(left_queue.peek()>right_queue.peek())){
            int tmp1=left_queue.poll();
            int tmp2=right_queue.poll();
            left_queue.add(tmp2);
            right_queue.add(tmp1);
        }
        sum++;
    }

    public double findMedian() {
        if(sum%2==0)
            return (left_queue.peek()+right_queue.peek())/2.0;
        else return left_queue.peek();
    }
}
