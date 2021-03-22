package 螺旋矩阵;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cwq
 * @link https://leetcode-cn.com/problems/spiral-matrix/solution/ju-zhen-bian-li-wen-ti-de-si-bu-qu-by-fu-91za/
 * @since 2021/03/17
 */

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        List<Integer> res = new ArrayList<Integer>();
        //四个边界
        int left = 0;
        int right = col - 1;
        int top = 0;
        int bottom = row - 1;
        //方向(0右  1下  2左  3上)
        int curDir = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, - 1}, {- 1, 0}};
        int x = 0, y = 0;
        int targetCount = row * col, i = 0;
        while (i < targetCount) {
            res.add(matrix[x][y]);
            i++;
            // 往右并且位于右边界上，需要调整方向
            if(curDir == 0 && y == right) {
                curDir++;
                top++;
            }
            else if(curDir == 1 && x == bottom) {
                curDir++;
                right--;
            }
            else if(curDir == 2 && y == left) {
                curDir++;
                bottom--;
            }
            else if(curDir == 3 && x == top) {
                curDir++;
                left++;
            }
            curDir %= 4;
            x += dirs[curDir][0];
            y += dirs[curDir][1];
        }
        return res;
    }
}
