package DP系列.线性DP.其他经典问题.俄罗斯套娃信封问题;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/20 13:25
 * @Description: [h, w]
 * 假如排序后数组为:
 * [2,5]
 * [5,4]
 * [6,7]
 * [7,8]
 * [7,2]
 * 我们发现，最长的套娃顺序是
 * [2,5]
 * [6,7]
 * [7,8]
 * 它们的h和w都是升序的
 * 也就是说问题可以转化为：求w：[5,4,7,8,2]的最长上升子序列的长度：[5,7,8]
 * 这里有一个排序的细节，就是对于h相同下w的排序是降序的：
 * 是因为如果w相同下按照h降序进行排序,
 * 那么我们在找w的最长上升子序列时就不会将w相同的加入长度计算（因为w之间不能套娃）
 * 动态规划时间复杂度O(n2)，空间复杂度为O(n)
 * @Url: https://leetcode-cn.com/problems/russian-doll-envelopes/
 * 思路来源：https://leetcode-cn.com/problems/russian-doll-envelopes/solution/zui-chang-di-zeng-zi-xu-lie-kuo-zhan-dao-er-wei-er/
 * 贪心+二分优化:https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/
 * @限制:
 * @Level:
 */
public class Solution {

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0)
            return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0])
                    return o1[0] - o2[0];
                else return o2[1] - o1[1]; //在w相同时，对h按照降序排列
            }
        });

        int res = 1;
        int len = envelopes.length;
        int[] dp = new int[len];  //dp[i]表示w[0]到w[i]的最大上升子序列的长度
        Arrays.fill(dp, 1);
        for (int i = 0; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) { //envelopes[i]的前面的所有序列
                if (envelopes[j][1] < envelopes[i][1]) {  //w[j]到w[i]是升序的，
                    dp[i] = Math.max(dp[j] + 1, dp[i]); //dp[j]+1表示w[0]到[j]最大上升子序列长度加上一个w[i]的长度
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] en = {{4, 5}, {4, 6}, {6, 7}, {2, 3}, {1, 1}};
        int res = new Solution().maxEnvelopes(en);
        System.out.println(res);
    }
}
