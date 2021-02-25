package 顺序二维数组查找;

/**
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 数组从右上角往左和下遍历数是分别递减和递增的
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 *
 */
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length==0||matrix[0].length==0)
            return false;
        int row=matrix.length;
        int col=matrix[0].length;  //数组按行存储
        int i=0,j=col-1;
        while(matrix[i][j]!=target){
            if(target<matrix[i][j]) //往左遍历
                j--;
            else  //往下遍历
                i++;
            if(i>=row||j<0) //超界
                return false;
        }
        return true;
    }
}
