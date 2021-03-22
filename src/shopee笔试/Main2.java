package shopee笔试;

import java.util.Scanner;

/**
 * @author cwq
 * @since 2021/03/17
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] s1 = str.replaceAll("],", "]#").split("#");
        String[][] arr = new String[s1.length][];
        for (int i = 0; i < arr.length; i++) {
            String[] s2 = s1[i].split(",");
            arr[i] = new String[s2.length];
            for (int j = 0; j < s2.length; j++) {
                arr[i][j] = s2[j].replaceAll("\\[|\\]", "");
            }
        }
        int row = arr.length;
        int col = arr[0].length;
        int[][] g = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                g[i][j] = Integer.parseInt(arr[i][j]);
            }
        }
        System.out.println(minPathSum(g));
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++)
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        for (int i = 1; i < m; i++)
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        return dp[m - 1][n - 1];
    }
}
