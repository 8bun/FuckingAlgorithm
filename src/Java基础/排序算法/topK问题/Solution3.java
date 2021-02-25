package Java基础.排序算法.topK问题;

import java.util.PriorityQueue;

/**
 * @description： 堆：只找到TopK，不排序TopK。
 * 假设找的是前k个最大的值，那么使用小顶堆先暂存数组的前k个值
 * 然后从第k+1个值开始，从外面找更大的值来替代堆顶
 * 如果建的是大顶堆，那么比它大的如果替换了堆顶，则原来堆顶的元素可能是原来前k大的，但是我们把它舍弃了
 * 反之，如果找前k个最小的值也同理
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/3 12:57
 * @level：
 */
public class Solution3 {
    public static void main(String[] args) {
        int[] nums = {6, 2, 5, 3, 1, 8, 9, 10, 4, 7, 855, - 1, 234};

        int k = 5;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        make_Heap(priorityQueue, k, nums);

        while (! priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.poll() + " ");
        }
    }

    private static void make_Heap(PriorityQueue<Integer> priorityQueue, int k, int[] nums) {
        int i = 0;
        //创建初始堆的过程O(klogk)
        for (; i < k; i++) {
            priorityQueue.offer(nums[i]);
        }
        //每次堆调整的时间复杂度O(logK)*次数(n-k)=O((n-k)logk,总得时间复杂度O(nlogk)
        for (; i < nums.length; i++) {
            if (nums[i] > priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.offer(nums[i]);
            }
        }
    }
}
