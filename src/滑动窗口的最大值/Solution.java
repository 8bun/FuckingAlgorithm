package 滑动窗口的最大值;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @description：
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值
 * @url： https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
 * @限制：
 * @author：Jack
 * @createTime：2020/4/3 9:57
 * @level：
 */
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null||nums.length==0)
            return new int[]{};
        //原本是(a,b)->(a-b)，表示升序，现在表示降序
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>((a,b)->(b-a));
        for(int i=0;i<k;i++){
            priorityQueue.add(nums[i]);
        }
        int index_res=0;
        int[] res=new int[nums.length-k+1];
        res[index_res++]=priorityQueue.peek();
        for(int i=k;i<nums.length;i++){
            priorityQueue.remove(nums[i-k]);
            priorityQueue.add(nums[i]);
            res[index_res++]=priorityQueue.peek();
        }
        return res;
    }
}
