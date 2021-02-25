package DP系列.博弈型DP.石子游戏II;

/**
 * @Author: Jack
 * @Date: 2020/7/7 12:40
 * @Description: 输入：piles = [2,7,9,4,4]
 * 输出：10
 * 解释：
 * 如果亚历克斯在开始时拿走一堆石子，李拿走两堆，接着亚历克斯也拿走两堆。在这种情况下，亚历克斯可以拿到 2 + 4 + 4 = 10 颗石子。
 * 如果亚历克斯在开始时拿走两堆石子，那么李就可以拿走剩下全部三堆石子。在这种情况下，亚历克斯可以拿到 2 + 7 = 9 颗石子。
 * 所以我们返回更大的 10。
 * 这里采用记忆化递归来解决
 * @Url: https://leetcode-cn.com/problems/stone-game-ii/
 * @限制: 1 <= piles.length <= 100
 * 1 <= piles[i] <= 10 ^ 4
 * @Level:
 */
public class Solution {

    private int[] sum;
    private int[][] memo;

    public int stoneGameII(int[] piles) {
        int len = piles.length;
        sum = new int[len];
        memo = new int[len][len];
        //初始化后缀和,sum[i]表示从piles[i]到piles[len-1]的和
        sum[len - 1] = piles[len - 1];
        for (int i = len - 2; i >= 0; i--) sum[i] = sum[i + 1] + piles[i];
        return dfs(piles, 0, 1);
    }

    /**
     * 返回当前选手在下面的状态下，能拿到的最多的石头量
     *
     * @param piles
     * @param start 开始选择石头的下标
     * @param m     这时的M，此次所选的石子堆数X，必须满足1 <= X <= 2m
     *              然后在下一次递归的m取值为Max(X, m)
     */
    public int dfs(int[] piles, int start, int m) {

        if (start >= piles.length) return 0;
        // 最大可拿的石子堆数大于剩余的石子堆数时，将剩余的石子全部拿走,
        // 所以当前选手在下面的状态下，能拿到的最多的石头量为sum[start]
        if (start + 2*m >= piles.length)
            return sum[start];

        if (memo[start][m] != 0) return memo[start][m];

        //从当前玩家的多种选择开始，求出下一位玩家选择能够拿到的石头量最少，就能保证当前玩家能够拿到的石头量最多
        int next_min = Integer.MAX_VALUE;
        for (int cur_select = 1; cur_select <= 2*m; cur_select++) {
            next_min = Math.min(next_min, dfs(piles, start + cur_select, Math.max(cur_select, m)));
        }
        return memo[start][m] = sum[start] - next_min;
    }
}
