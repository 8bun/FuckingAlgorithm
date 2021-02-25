package vivo数位之积; /**
 * @Author: Jack
 * @Date: 2020/6/24 21:22
 * @Description:
 * @Url: https://www.nowcoder.com/questionTerminal/f9b86bcd95a643138f9593966a5495b8
 * @限制:
 * @Level:
 */


public class Solution {
    /**
     * 输入一个整形数值，返回一个整形值
     * @param n int整型 n>9
     * @return int整型
     */
    int res = 0;
    public int solution (int n) {
        // write code here
        if(n < 9) return 10 + n;
        return fun(0, 1, n);
    }

    public int fun(int cur, int mult, int n) {

        if(n == 1)
            return cur;
        int num = 9;
        for(; num >= 2; num--) {
            if(n % num == 0) {
                int newCur = num * (int)(Math.pow(10, String.valueOf(cur).length())) + cur;
                if(cur == 0) newCur = num;
                return fun(newCur, num * mult, n/num);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int res = new Solution().solution(108);
        System.out.println(res);
    }
}
