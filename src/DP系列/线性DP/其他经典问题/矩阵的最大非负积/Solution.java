package DP系列.线性DP.其他经典问题.矩阵的最大非负积;

/**
 * @Author: Jack
 * @Date:
 * @Description:
 * @Url: https://leetcode-cn.com/problems/maximum-non-negative-product-in-a-matrix/comments/
 */
public class Solution {

    private static final int MOD = (int) (1e9 + 7);
    public int maxProductPath(int[][] grid) {
        int row = grid.length, col = grid[0].length;

        //dp[i][j]存从(0,0)到i,j路径的最非负积
        long[][] dpMin = new long[row][col];
        long[][] dpMax = new long[row][col];
        dpMax[0][0] = dpMin[0][0] = grid[0][0];

        for (int i = 1; i < row; i++) {
            dpMin[i][0] = dpMax[i][0] = dpMax[i - 1][0] * grid[i][0];
        }
        for (int i = 1; i < col; i++) {
            dpMin[0][i] = dpMax[0][i] = dpMax[0][i - 1] * grid[0][i];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if(grid[i][j] < 0) {
                    dpMin[i][j] = Math.max(dpMax[i - 1][j], dpMax[i][j - 1]) * grid[i][j];
                    dpMax[i][j] = Math.min(dpMin[i - 1][j], dpMin[i][j - 1]) * grid[i][j];
                }
                else if (grid[i][j] > 0){
                    dpMin[i][j] = Math.min(dpMin[i - 1][j], dpMin[i][j - 1]) * grid[i][j];
                    dpMax[i][j] = Math.max(dpMax[i - 1][j], dpMax[i][j - 1]) * grid[i][j];
                }
                else {
                    dpMin[i][j] = dpMax[i][j] = 0;
                }
            }
        }
        return dpMax[row - 1][col - 1] < 0 ? -1 : (int) (dpMax[row - 1][col - 1] % MOD);
    }
}
