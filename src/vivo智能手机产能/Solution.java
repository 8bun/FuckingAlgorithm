package vivo智能手机产能;

/**
 * @author cwq
 * @since 2020/6/24 20:52
 * @Description:
 * @link https://www.nowcoder.com/question/next?pid=22390442&qid=925106&tid=34306402
 * @限制:
 * @Level:
 */

public class Solution {
    public static void main(String[] args) {
        int res = new Solution().solution(11);
        System.out.println(res);
    }

    /**
     * @param n int整型 第n天
     *
     * @return int整型
     */
    public int solution(int n) {
        // write code here
        int res = 0;
        int day = 0;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            day += i;
            if (day > n) {
                res += (n - (day - i)) * i;
                break;
            }
            res += (i * i);
        }
        return res;
    }
}
