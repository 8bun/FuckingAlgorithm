package DFS系列.矩阵中的最长递增路径;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/7/15 19:47
 * @Description: 记忆化深度优先搜索
 * 另外一种做法：
 * 拓扑排序做法
 * 如果把每个格子当作一个点，然后从数值小的点向四周比它大的点连一条有向边，
 * 最终一定会形成一个有向无环图，问题就转变成了求有向无环图中的最长路径。
 * 方法是先找到所有入度为 0 的结点，然后放入一个队列，依次从队列里取出结点，
 * 从图中删除这些结点。然后图中就出现了新的入度为 0 的结点了，它们路径长度加 1 。
 * 接着重复上面的操作，直到最后没有结点。
 * 见拓扑排序系列
 * @Url: https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/
 * @限制:
 * @Level:
 */
public class Solution {

    private int[][] dirs = {{1, 0}, {- 1, 0}, {0, 1}, {0, - 1}};
    private int[][] memo; //memo[i][j]表示dfs(i,j)的结果

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        memo = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(i, j, matrix, m, n));
            }
        }
        return ans;
    }

    private int dfs(int i, int j, int[][] matrix, int m, int n) {
        if (memo[i][j] != 0) return memo[i][j];

        int max = 1;
        for (int[] dir : dirs) {
            int newX = i + dir[0];
            int newY = j + dir[1];
            if (newX < 0 || newY < 0 || newX >= m || newY >= n || matrix[newX][newY] <= matrix[i][j]) continue;
            max = Math.max(max, 1 + dfs(i + dir[0], j + dir[1], matrix, m, n));
        }
        memo[i][j] = max;
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
