package 螺旋矩阵II;

/**
 * @author cwq
 * @since 2021/03/24
 * @link https://leetcode-cn.com/problems/spiral-matrix-ii/solution/spiral-matrix-ii-mo-ni-fa-she-ding-bian-jie-qing-x/
 */

public class Solution {

    public int[][] generateMatrix(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, target = n * n;
        while (num <= target) {
            for (int i = l; i <= r; i++) {
                mat[t][i] = num++;
            }
            t++;
            for (int i = t; i <= b; i++) {
                mat[i][r] = num++;
            }
            r--;
            for (int i = r; i >= l; i--) {
                mat[b][i] = num++;
            }
            b--;
            for (int i = b; i >= t; i--) {
                mat[i][l] = num++;
            }
            l++;
        }
        return mat;
    }
}
