package 贪心系列.跳跃游戏II;

import java.util.Scanner;

/**
 * <a href="https://grandyang.com/leetcode/45/">...</a>
 * 为了较快的跳到末尾，想知道每一步能跳的范围，这里贪婪并不是要在能跳的范围中选跳力最远的那个位置，因为这样选下来不一定是最优解，
 * 这么一说感觉又有点不像贪婪算法了。其实这里贪的是一个能到达的最远范围，遍历当前跳跃能到的所有位置，然后根据该位置上的跳力来预测下一步能跳到的最远距离，贪出一个最远的范围，
 * 一旦当这个范围到达末尾时，当前所用的步数一定是最小步数。需要两个变量 cur 和 pre 分别来保存当前的能到达的最远位置和之前能到达的最远位置，只要 cur 未达到最后一个位置则循环继续，
 * pre 先赋值为 cur 的值，表示上一次循环后能到达的最远位置，如果当前位置i小于等于 pre，说明还是在上一跳能到达的范围内，根据当前位置加跳力来更新 cur，
 * 更新 cur 的方法是比较当前的 cur 和 i + A[i] 之中的较大值，如果题目中未说明是否能到达末尾，还可以判断此时 pre 和 cur 是否相等，如果相等说明 cur 没有更新，即无法到达末尾位置，返回 -1
 */

public class Solution {
    public static void main(String[] args) {

        int[] nums = {3, 2, 1, 0, 4};
        int r = new Solution().jump(nums);
        System.out.println(r);
    }

    public int jump(int[] nums) {
        int res = 0, i = 0, maxIdx = nums.length, cur = 0;

        while (cur < maxIdx - 1) {
            res++;
            int pre = cur; //pre 先赋值为 cur 的值，表示上一次循环后能到达的最远位置

            // 当前位置i小于等于 pre，说明还是在上一跳能到达的范围内, 根据当前位置加跳力来更新 cur
            for (; i <= cur; i++) {
                cur = Math.max(cur, nums[i] + i);
            }
            // 如果题目中未说明是否能到达末尾，还可以判断此时 pre 和 cur 是否相等，
            // 如果相等说明 cur 没有更新，即无法到达末尾位置，返回 -1
            if (cur == pre) {
                return -1;
            }
        }
        return res;
    }
}

