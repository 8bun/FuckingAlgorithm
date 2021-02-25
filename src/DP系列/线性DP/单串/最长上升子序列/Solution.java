package DP系列.线性DP.单串.最长上升子序列;

import java.util.Arrays;

/**
 * @description： dp[i]表示以num[i]为结尾的所有子序列中上升子序列的最大长度，
 * 首先，假设num为：[1,2,3,4]，那么以num[3]结尾的所有子序列中上升子序列的最大长度，可以这样计算：
 * 如果num[3]前面的所有数字，设j为[0,3-1],在num[j]中，与num[3]的大小，分两种情况
 * num[j]<num[3],那么可以更新dp[3]为dp[j]+1，因为num[j],num[3]已经是上升的了，其实就理解为
 * 以num[3]为结尾的所有子序列中上升子序列的最大长度此时转化为以比num[3]小的num[j]结尾的所有子序列中上升子序列的最大长度+1
 * 由于dp[j]+1和dp[3]，不知道哪个大，所以要取其最大值作为dp[3]，
 * 如果num[j]>=num[3],则没有任何更新
 * 最后，再取dp[i]中的最大值就是所有情况的最大上升序列长度
 * @url： https://leetcode-cn.com/problems/longest-increasing-subsequence/
 * @限制： 子序列可不连续，但是在数组中的相对顺序要不变
 * @author： Jack
 * @createTime： 2020/3/27 11:57
 * @level：
 */
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        int res = 1;
        int[] dp = new int[len];
        Arrays.fill(dp, 1); //初始化，到自己的现在最大上升长度都为1

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i - 1; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
