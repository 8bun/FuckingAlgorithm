package 回文数;

/**
 * 如果是负数则一定不是回文数，直接返回 false
 * 如果是正数，则将其倒序数值计算出来，然后比较和原数值是否相等
 * 如果是回文数则相等返回 true，如果不是则不相等 false
 * 比如 123 的倒序 321，不相等；121 的倒序 121，相等
 *
 * @author cwq
 * @link https://leetcode-cn.com/problems/palindrome-number/solution/hua-jie-suan-fa-9-hui-wen-shu-by-guanpengchn/
 * @since 2021/3/9
 */
class Solution {
    public static void main(String[] args) {

        System.out.println(new Solution().isPalindrome(10));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        //要计算的数
        int cur = 0;
        int tempX = x;
        while (tempX != 0) {
            cur = cur * 10 + tempX % 10;
            tempX /= 10;
        }
        return cur == x;
    }
}
