package DP系列.博弈型DP.石子游戏IV;
import java.util.Scanner;
/**
 * @Author: Jack
 * @Date: 2020/7/12 09:53
 * @Description: 
 * @Url: https://leetcode-cn.com/problems/stone-game-iv
 * @限制: 
 * @Level: 
 */
public class Solution {

    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1]; //dp[left]表示当前剩余n堆石子时，第一个玩家能否胜出
        for (int left = 1; left <= n; left++) {
            //枚举下一位玩家的选择
            for (int nextSelect = 1; left - nextSelect * nextSelect >= 0; nextSelect++) {
                //dp[left - nextSelect * nextSelect]表示当前剩余left - nextSelect * nextSelect堆石子时（被另外一个玩家选去了nextSelect * nextSelect堆）
                //第一个玩家能否胜出；那么第二个玩家如果赢了，就表示第一个玩家输了，相反也是，所以要取反
                //其实这里相当于只有1个玩家在玩，只是通过left - nextSelect * nextSelect来表示被选走一定量之后的选择情况，也就是代表了2个人
                dp[left] = dp[left] || !dp[left - nextSelect * nextSelect];
                if (dp[left]) break; //如果已经赢了，就直接退出
            }
        }
        return dp[n];
    }
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
    }
}
