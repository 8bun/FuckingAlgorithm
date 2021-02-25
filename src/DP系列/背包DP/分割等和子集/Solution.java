package DP系列.背包DP.分割等和子集;


/**
 * @description：
 * 01背包的变体：
 * dp[i][j]表示从数组的 [0, i] 这个子区间内挑选一些正整数，每个数只能用一次，使得这些数的和恰好等于 j
 * 所以结果是求dp[N][sum/2]
 * @url： https://leetcode-cn.com/problems/partition-equal-subset-sum/
 * @限制：
 * @author：Jack
 * @createTime：2020/4/10 20:04
 * @level：
 */
public class Solution {
    public boolean canPartition(int[] nums) {
        int sum=0;
        for (int num:nums)
            sum+=num;
        if((sum&1)!=0)  //奇数
            return false;
        int len=nums.length;
        sum=sum/2;
        boolean[][] dp=new boolean[len][sum+1];
        for (int i=0;i<=sum;i++){
            //仅仅使用一个nums[0]能否构成和i
            if(i!=nums[0]){
                dp[0][i] = false;
            }else{
                dp[0][i] = true;
            }
        }

        for (int i=1;i<len;i++){
            for (int j=0;j<=sum;j++){
                if(nums[i]>j){
                    dp[i][j]=dp[i-1][j];  //即使最后一个参与不了，但是前面可以参与
                }
                else dp[i][j]=dp[i-1][j]||dp[i-1][j-nums[i]];
            }
        }
        return dp[len-1][sum];
    }
}
