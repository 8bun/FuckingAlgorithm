package DP系列.博弈型DP.石子游戏;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/6/27 12:45
 * @Description: 偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。
 * 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 * "假设亚历克斯和李都发挥出最佳水平"，当亚历克斯赢得比赛时返回 true，当李赢得比赛时返回 false。
 * 设dp[i][j]代表从piles下标 i到j的j-i+1堆石子选完后（XX先选）,当前选手XX相对于对手能够多出的石子数
 * 若 i==j，则明显当前选手相对于对手多出的石子数为 f(i,j)=piles[i]，因为只有一堆石子，先选之后就没有石头可选了
 * 若有两堆或多堆石子，则 f(i,j)=max(piles[i]-f(i+1,j), piles[j]-f(i,j-1))，
 * 其中 f(i+1,j)表示对手相对于当前选手多出的石子，当前选手A选择 piles[i]，所以得分+piles[i]，然后
 * -f(i+1,j)可以看成当前选手A比对手多了-f(i+1,j)这么多分，实际上是在前面+piles[i]基础上减去下一轮对手的得分,得到当前自己的得分
 * 所以当前选手相对于对手多出的石子为 piles[i]-f(i+1,j)。同理对于 piles[j]-f(i,j-1)) 。所以当前选手取两种情况中的最大值
 * 由状态方程可知，式子都要求我们在求dp[i][j]的时候知道它的下面和左边一个格子的值，所以我们从下到上，从左到右计算填表。
 * @Url: https://leetcode-cn.com/problems/stone-game/
 * @限制:
 * @Level:
 */
public class Solution {

    public boolean stoneGame(int[] piles) {
        if (piles == null || piles.length == 0) return false;
        int len = piles.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) dp[i][i] = piles[i];
        //这里也可以使用区间dp的做法，通过起点i和区间长度确定j的位置
        //或者二维降1维
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] > 0;
    }

    /**
     * 1维
     * @param piles
     * @return
     */
    public boolean stoneGame1(int[] piles) {
        if (piles == null || piles.length == 0) return false;
        int len = piles.length;
        int[] dp = new int[len];

        for (int i = len - 2; i >= 0; i--) { //还是区间起点的作用
            for (int j = i + 1; j < len; j++) {
              //dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
                //dp[i+1][j]位于dp[i][j]的下一行的格子，dp[i][j - 1]位于左边
                //由于采用从下到上，从左到右，那么下的先，左边的是dp[i][j]的"当前"状态
                //下一行的是dp[i][j]的前一个状态
                //每一行的每一列保留各自的状态
                //在遍历过程中不断更新即可
                //那么dp[i+1][j]就是当前第j列的状态dp[j]，dp[i][j-1]就是第j-1的状态
                //然后用这两个状态更新当前第j列的状态
                //最后更新到的dp[len-1]就是最后一列的状态,既然这里piles[i]没有去掉，那么i还是起到了区间的作用
                //也就是说到了外层最后一层循环i=0后，dp[len-1]更新到的最后的一个值就是我们要求的dp[0][len-1]
                dp[j] = Math.max(piles[i] - dp[j], piles[j] - dp[j - 1]);
            }
        }
        return dp[len - 1] > 0;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] p = {1,3,7,5};
        boolean res = new Solution().stoneGame(p);
        System.out.println(res);
    }
}
