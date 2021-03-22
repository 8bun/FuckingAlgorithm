package 平方数之和;

/**
 * 二分查找
 *
 * @author cwq
 * @link https://leetcode-cn.com/problems/sum-of-square-numbers/solution/ping-fang-shu-zhi-he-by-leetcode/
 * @since 2021/03/19
 */

public class Solution {

    public boolean judgeSquareSum(int c) {
        // a*a + b*b = c
        for (long a = 0; a * a <= c; a++) {
            int b = (int) (c - a * a);
            if(binary_search(0, b, b)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 在[start, end](一个递增区间)二分查找是否有整数的平方等于target
     * @param start
     * @param end
     * @param target
     * @return
     */
    private boolean binary_search(long start, long end, long target) {
        if(start > end) {
            return false;
        }
        long mid = start + (end - start) / 2;
        if(mid * mid == target) {
            return true;
        }
        if(mid * mid > target) {
            return binary_search(start, mid - 1, target);
        }
        return binary_search(mid + 1, end, target);
    }
}
