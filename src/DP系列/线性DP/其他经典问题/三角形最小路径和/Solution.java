package DP系列.线性DP.其他经典问题.三角形最小路径和;

import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/23 21:46
 * @Description:
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）
 * 看成二维数组
 * 2
 * ↓ ↘
 * 3  4
 * ↓ ↘↓ ↘
 * 6  5  7
 *      ↘↓
 * 4  1  8  3
 * 动规：自顶向下
 * 设dp[i][j]表示包含traiangle[i][j]的最小路径
 * 通过上面箭头可以看出，除了第一行外，每一行的最右的元素都是由上面一行的最后一个元素走到的
 * 每一行最左的元素都是由上一行的最左的元素走到的，其他元素都可以由它的上方和左上方元素走到，所以应该从包含它的上方或左上方元素的最小值选择
 * 应该从哪里走过来
 * dp一直记录的值是这样变化的：
 * 2
 * 5   6
 * 9   10   13
 * 13  11   18  16
 * 那么最小值为11
 * 自底向上：
 * 2
 * ↑ ↖
 * 3  4
 * ↑ ↖↑ ↖
 * 6  5  7
 * ↑ ↖↑ ↖↑ ↖
 * 4  1  8  3
 * 除最后一行外，每一行的元素都可以由它下方和右下方的元素走过来，但是应该选择的是路径小的走过来
 * 那么dp记录的值应该是这样变化的
 * 11
 * 9  10
 * 7  6  10
 * 4  1  8  3
 * @Url: https://leetcode-cn.com/problems/triangle/
 * @限制: 每一步只能移动到下一行中相邻的结点上。
 * @Level:
 */
public class Solution {
    /**
     * 自顶向下
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int row=triangle.size();
        int column=triangle.get(row-1).size();
        int[][] dp=new int[row][column];
        for (int i=0;i<column;i++)
             dp[row-1][i]=triangle.get(row-1).get(i);

        for (int cur_level=row-2;cur_level>=0;cur_level--){
            for (int i=0;i<=cur_level;i++)  //遍历该行的所有数字
                dp[cur_level][i]=Math.min(dp[cur_level+1][i],dp[cur_level+1][i+1])+triangle.get(cur_level).get(i);
        }
        return dp[0][0];
    }

    /**
     * 自底向上
     * @param triangle
     * @return
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        int row=triangle.size();
        int column=triangle.get(row-1).size();
        int[][] dp=new int[row][column];
        for (int i=0;i<column;i++)
            dp[row-1][i]=triangle.get(row-1).get(i);

        for (int cur_level=row-2;cur_level>=0;cur_level--){
            for (int i=0;i<=cur_level;i++)  //遍历该行的所有数字
                dp[cur_level][i]=Math.min(dp[cur_level+1][i],dp[cur_level+1][i+1])+triangle.get(cur_level).get(i);
        }
        return dp[0][0];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
