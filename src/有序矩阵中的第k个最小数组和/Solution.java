package 有序矩阵中的第k个最小数组和;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/5/3 13:32
 * @Description:
 * @link https://leetcode-cn.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/
 * @限制:
 * @Level:
 */
public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }

    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ans.add(mat[0][i]);
        }
        for (int i = 1; i < m; i++) {
            List<Integer> curSum = new ArrayList<>();
            //前面的每一个和都加给新行
            for (int old : ans) {
                for (int curNewNum : mat[i]) {
                    curSum.add(old + curNewNum);
                }
            }
            Collections.sort(curSum); //每次处理完一行的和，对总的结果进行排序，然后取前k个数据进行下一次的循环
            ans.clear();//更新ans
            for (int l = 0; l < Math.min(k, curSum.size()); l++) {
                ans.add(curSum.get(l));
            }
        }
        return ans.get(k - 1);
    }
}
