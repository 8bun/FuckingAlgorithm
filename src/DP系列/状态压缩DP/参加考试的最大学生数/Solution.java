package DP系列.状态压缩DP.参加考试的最大学生数;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/20 23:08
 * @Description:
 * 这题也可以采用二分图来解决（后面再补）
 * @Url: https://leetcode-cn.com/problems/maximum-students-taking-exam/submissions/
 * @限制:
 * 1 <= seats.length <= 8
 * 1 <= seats[i].length <= 8
 * @Level:
 */
public class Solution {

    public int maxStudents(char[][] seats) {
        int m = seats.length;
        if (m == 0) { return 0; }
        int n = seats[0].length;
        if (n == 0) { return 0; }
        //m * n
        int ans = 0;
        //每一行有1<<n种状态(n个位，每个位有1和0两种情况)
        //dp[i][state]表示seats[0...i]行中，seats[i]行状态为state时最大的可坐学生个数
        int[][] dp = new int[m][1 << n];
        for (int row = 0; row < m; row++){
            for (int state = 0; state < (1 << n); state++){
                int cntOne = isValid(state, seats, row, n);
                if (cntOne == -1){
                    continue;
                }
                if (row == 0) {
                    dp[row][state] = cntOne;continue;
                }

                //判断上一排的座位是否与state冲突
                for (int lastState = 0; lastState < (1 << n); lastState++) {
                    if (((state << 1) & lastState) != 0 || ((state >> 1) & lastState) != 0) {
                        continue;
                    }
                    dp[row][state] = Math.max(dp[row][state], dp[row - 1][lastState] + cntOne);
                    ans = Math.max(dp[row][state], ans);
                }
            }
        }
        return ans;
    }
    /**
     * state是我们自己的座位安排，为1则必须有人坐，0则没人坐
     * 首先，判断state是否存在(往左往右)相邻位置为1，如果存在则非法
     * 例如state='110'则非法，我们可以考虑将state左移和右移一位，分别和state相与，得到不为0时就非法
     * 如：
     *  110
     * &010
     * =010 (非法，因为左移和右移一位，对应每一位相与都可以得到与该位相邻的位置的相与结果，都1则结果为1也就是相邻了)
     *
     * 其次，要判断我们自己安排的state是不是符合题目给的安排
     * 即原本应该是不能坐的变成可以坐的，也就是判断seats[i]的二进制和
     * state的二进制对应位是否存在前者为0后者为1的情况，所以我们就可以
     * 遍历state的每一位，然后查看与前者对应位的情况，无疑就四种情况：0 1, 1 0, 1 1, 0 0
     * 其中只要出现0 1,那么state就不合法，
     * 我们发现将前者取反，然后和后者相与的结果：
     * 0 1 => 1&1 = 1
     * 1 0 => 0&0 = 0
     * 1 1 => 0&1 = 0
     * 0 0 => 1&0 = 0
     * 所以要判断state是否合法，只要判断是否对应位的上面的与操作是否出现了!=0的情况即可
     * 到了这里，state已经是合法的了
     * 最后，是要判断根据上一排的座位情况来决定state的情况是否发生冲突。
     * 也就是判断state的左前和右前是否都为1。这里也可以通过state左移1位和右移1位然后和上一排的座位情况进行相与，得到非0则非法
     * 由于上一排的座位情况可能有多种，那么我们可以枚举0~(1<<n)的所有状态即可
     * 还有，这里为了进行1的计数，我们可以在判断过程中进行
     * /
    /**
     *
     * @param state 当前状态
     * @param seats 为了获取题目给的状态
     * @param row 当前行号
     * @param n n列
     * @return
     */
    private int isValid(int state, char[][] seats, int row, int n){
        int cntOne = 0;

        for (int index = 0; index < n; index++){
            int cur = (1 << index) & state;
            if (cur != 0) {
                cntOne++;
            }
            //判断是否有相邻的1
            if ((state & (state << 1)) != 0 || (state & (state >> 1))!=0) {
                return -1;
            }
            //判断是否与题目不符
            int rule = compress(seats, row, n);
            int curR = rule & (1 << index);
            if (((~curR) & cur) != 0) {
                return - 1;
            }
        }
        return cntOne;
    }

    /**
     * 将row行压缩成0,1的形式
     * @param seats
     * @param row
     * @param n n列
     * @return
     */
    private int compress(char[][] seats, int row, int n){

        int res = 0;
        for (int i = n - 1; i >= 0; i--){
            if (seats[row][i] == '.') {
                res += (1 << (n - 1 - i));
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] seats = {
//                {'#','.','#','#','.','#'},
//                {'.','#','#','#','#','.'},
//                {'#','.','#','#','.','#'}
                {'#','#','#'},
                {'.','#','#'}
        };
        int max = new Solution().maxStudents(seats);
        System.out.println(max);

    }
}
