package 二分查找系列.x的平方根;

/**
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 比如sqrt(5)=2
 * @author cwq
 * @since 2021/03/25
 * @link https://leetcode-cn.com/problems/sqrtx/solution/x-de-ping-fang-gen-by-leetcode-solution/
 */
public class Solution {
    public int mySqrt(int x) {
        if(x <= 1) {
            return x;
        }
        int left = 0, right = x;
        // 例如x=5,则1^2=1,2^2=4<5,3^2=9>5，所以返回2
        // 如果是小数，返回下一个整数，例如根号的结果是2.111222，循环最后返回3
        while (left < right) {

            int mid = left + (right - left) / 2;
            if((long)mid * mid <= x) {
                left = mid + 1;
            }
            // 出现了mid*mid>x，right可能就是循环最后要返回的数
            else {
                right = mid;
            }
        }
        // 题目要求去掉小数
        return left - 1;
    }
}
