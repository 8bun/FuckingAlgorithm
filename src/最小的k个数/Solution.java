package 最小的k个数;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 堆做法
 * @link https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 * @author cwq
 */
public class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if(k == 0 || arr.length == 0) {
            return new int[]{};
        }
        Queue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num : arr) {
            if(pq.size() < k) {
                pq.offer(num);
            }
            else if(num < pq.peek()) {
                pq.poll();
                pq.offer(num);
            }
        }
        int[] res = new int[pq.size()];
        int idx = 0;
        for (int num : pq) {
            res[idx++] = num;
        }
        return res;
    }
}
