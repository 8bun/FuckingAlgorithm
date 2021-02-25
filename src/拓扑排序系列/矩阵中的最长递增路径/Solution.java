package 拓扑排序系列.矩阵中的最长递增路径;
import java.util.Deque;
import java.util.LinkedList;
/**
 * @Author: Jack
 * @Date: 2020/7/15 21:39
 * @Description:
 * 拓扑排序做法
 * 如果把每个格子当作一个点，然后从数值小的点向四周比它大的点连一条有向边，
 * 最终一定会形成一个有向无环图，问题就转变成了求有向无环图中的最长路径。
 * 方法是先找到所有出度为 0 的结点，然后放入一个队列，依次从队列里取出结点，
 * 从图中删除这些结点。然后图中就出现了新的出度为 0 的结点了，它们路径长度加 1 。
 * 接着重复上面的操作，直到最后没有结点。
 * 不过这道题跟课程表不太一样，在出队的时候当当前所有的叶子结点（出度为0）全部出队时，height++，直到之后队列为空返回height
 * 可以把构建的有向无环图想象成一颗树（树只是分析简单的一种情况），这个树从根结点开始到每个根结点都是一条条的递增路径
 * 所以此时是求树的最大深度，那么以这样来理解前面height++的情景会好理解许多
 * @Url: https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/
 * @限制: 
 * @Level: 
 */
public class Solution {

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        int[][] outDegrees = new int[m][n];
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] dir : dirs) {
                    int newX = i + dir[0];
                    int newY = j + dir[1];
                    if (!verify(newX, newY, m, n)) continue;
                    //统计每个点的出度
                    if (matrix[i][j] < matrix[newX][newY]) ++outDegrees[i][j];
                }
            }
        }
        //所有出度为0的点(坐标)加入队列
        Deque<int[]> deque = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (outDegrees[i][j] == 0) deque.add(new int[]{i, j});
            }
        }
        int height = 0;
        while (!deque.isEmpty()) {
            height++;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int[] curLeaves = deque.poll();
                //更新叶子结点关联的结点的出度-1
                for (int[] dir : dirs) {
                    int neighborX = curLeaves[0] + dir[0];
                    int neighborY = curLeaves[1] + dir[1];
                    if (!verify(neighborX, neighborY, m,n)) continue;
                    if (matrix[neighborX][neighborY] < matrix[curLeaves[0]][curLeaves[1]]){
                        if (--outDegrees[neighborX][neighborY] == 0) deque.add(new int[]{neighborX, neighborY});
                    }
                }
            }
        }
        return height;
    }
    private boolean verify(int i, int j, int m, int n)  {
        return i >= 0 && j >= 0 && i < m && j < n;
    }
}
