package BFS系列.地图分析;

import java.util.ArrayDeque;

/**
 * BFS
 * @author cwq
 * @since 2021/02/21
 * @link https://leetcode-cn.com/problems/as-far-from-land-as-possible/solution/jian-dan-java-miao-dong-tu-de-bfs-by-sweetiee/
 */
public class Solution1 {

    private final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int maxDistance(int[][] grid) {
        ArrayDeque<int[]> arrayDeque = new ArrayDeque<>();
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    arrayDeque.offer(new int[]{i, j});
                }
            }
        }
        int[] point = null;
        boolean hasOcean = false;
        while (!arrayDeque.isEmpty()) {
            point = arrayDeque.poll();
            int x = point[0], y = point[1];
            for (int[] d : dir) {
                int newX = d[0] + x, newY = d[1] + y;
                if (newX < 0 || newY >= col || newY < 0 || newX >= row || grid[newX][newY] != 0) {
                    continue;
                }
                grid[newX][newY] = grid[x][y] + 1;
                hasOcean = true;
                arrayDeque.offer(new int[]{newX, newY});
            }
        }
        if (point == null || !hasOcean) {
            return -1;
        }
        return grid[point[0]][point[1]] - 1;
    }
}
