package 二分系列.二维矩阵二分.搜索二维矩阵;import java.util.Scanner;
/**
 * @Author: Jack
 * @Date: 2020/7/4 09:35
 * @Description:
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * - 每行中的整数从左到右按升序排列。
 * - 每行的第一个整数大于前一行的最后一个整数。
 *
 * 对于m*n的二维矩阵，化为一维矩阵进行二分，有个规律，对于
 * 化成的一维矩阵之后进行二分的中点序号mid，由于在二维矩阵是按行排列的，所以可以根据n算出其在二维矩阵中的
 * 坐标(i,j)，其中i=mid除以n(向上取整),j=mid%n
 * @Url: https://leetcode-cn.com/problems/search-a-2d-matrix/
 * @限制: 
 * @Level: 
 */
public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        int left = 0, right = m * n - 1;
        int pivotIdx, pivotElement; //中点及中点元素
        while (left <= right) {
            pivotIdx = (left + right) / 2;
            pivotElement = matrix[pivotIdx / n][pivotIdx % n];
            if (target == pivotElement) return true;
            //小于，大于的情况
            else {
                if (target < pivotElement) {
                    right = pivotIdx - 1;
                }
                else left = pivotIdx + 1;
            }
        }
        return false;
    }
    public static void main(String[] args){
         Scanner scanner=new Scanner(System.in);
    }
}
