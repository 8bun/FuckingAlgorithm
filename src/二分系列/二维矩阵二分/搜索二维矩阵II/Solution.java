package 二分系列.二维矩阵二分.搜索二维矩阵II;import java.util.Scanner;
/**
 * @Author: Jack
 * @Date: 2020/7/4 09:49
 * @Description:、
 * 本题不采用二分做会比较快
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * - 每行的元素从左到右升序排列。
 * - 每列的元素从上到下升序排列。
 * 如：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 首先，我们初始化一个指向矩阵'左下角'的 (row，col) 指针。
 * 然后，直到找到目标并返回 true（或者指针指向矩阵维度之外的 (row，col) 为止，
 * 我们执行以下操作：如果当前指向的值大于目标值，则可以 “向上” 移动一行。
 * 否则，如果当前指向的值小于目标值，则可以移动一列。
 * 不难理解为什么这样做永远不会删减正确的答案；因为行是从左到右排序的，所以我们知道当前值右侧的每个值都较大。
 * 因此，如果当前值已经大于目标值，我们知道它右边的每个值会比较大。也可以对列进行非常类似的论证，
 * 因此这种搜索方式将始终在矩阵中找到目标（如果存在）。
 * 对于四个角的选择：
 * 选左上角，往右走和往下走都增大，不能选
 * 选右下角，往上走和往左走都减小，不能选
 * 选左下角，往右走增大，往上走减小，可选
 * 选右上角，往下走增大，往左走减小，可选
 *
 * 时间复杂度：O(n+m)。
 * 时间复杂度分析的关键是注意到在每次迭代（我们不返回 true）时，行或列都会精确地递减/递增一次。
 * 由于行只能减少 m 次，而列只能增加 n 次，因此在导致 while 循环终止之前，
 * 循环不能运行超过 n+m 次。因为所有其他的工作都是常数，所以总的时间复杂度在矩阵维数之和中是线性的。
 * 空间复杂度：O(1)，因为这种方法只处理几个指针，所以它的内存占用是恒定的。
 *
 * 如果采用二分法，
 * 二分查找的思想是沿着对角线，行查找一下，列查找一下
 * @Url: https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 * @限制: 
 * @Level: 
 */
public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        int x = m - 1, y = 0;
        while (x >= 0 && y < n) {
            if (target == matrix[x][y]) return true;
            if (target > matrix[x][y]) y++;
            else x--;
        }
        return false;
    }
    public static void main(String[] args){
         Scanner scanner=new Scanner(System.in);
    }
}
