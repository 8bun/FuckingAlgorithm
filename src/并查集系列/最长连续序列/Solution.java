package 并查集系列.最长连续序列;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/8 13:13
 * @Description:
 *
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 由于是O(n)复杂度，所以需要进行线性遍历。
 * 我们维护一个HashMap,key为num，value为当前和它组合的最长连续递增序列的长度
 * 比如[100, 4, 200, 1, 3, 2]
 * 首先100->1,4->1,200->1,1->1
 * 这时遍历到3，发现它的右4->1,所以更新4->2,3->2
 * 遍历到2,发现它的左1->1,右3->2,所以更新2->1(左)+1(本身)+2(右)=4,1->4,4->4
 * 只更新当前2所在的最长连续序列的边界以及它本身即可，因为最长连续序列的扩增只是与边界有关
 * 如果访问到重复的直接跳过，因为题目要求的连续序列必须是严格的递增
 * 最后返回max_value
 * @Url: https://leetcode-cn.com/problems/longest-consecutive-sequence
 * https://www.bilibili.com/video/BV1M7411A79B
 * @限制:  要求算法的时间复杂度为 O(n)。
 * @Level:
 */
public class Solution {
    private HashMap<Integer,Integer> map=new HashMap<>();
    public int longestConsecutive(int[] nums) {
        if (nums.length==0)
            return 0;
        int max_len=0;
        for (int num : nums){
            if (map.containsKey(num))continue;
            int left_len=0, right_len=0;
            if (map.containsKey(num-1))
                left_len=map.get(num-1);
            if (map.containsKey(num+1))
                right_len=map.get(num+1);
            int cur_len=left_len+1+right_len;
            map.put(num,cur_len);
            max_len=Math.max(max_len,cur_len);
            //更新边界
            if (map.containsKey(num-left_len))
                map.put(num-left_len,cur_len);
            if (map.containsKey(num+right_len))
                map.put(num+right_len,cur_len);
        }
        return max_len;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums= {5,2,1,3,100,100,0};
        int max=new Solution().longestConsecutive(nums);
        System.out.println(max);
    }
}
