package DP系列.区间DP.戳气球;

import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/5/12 20:00
 * @Description: DP解法
 * 注意这里有个子问题依赖的陷阱
 * 假设气球区间为：[3,1,5,8]，我们要求这个区间所能获得硬币的最大数量x
 * 那么假设我们先戳破1，那么x=[3]+3*1*5+[5,8]
 * 此时两个子问题[3]、[5,8]互相独立，也就是对于子问题[3]，第二次戳气球3时，所得硬币的最大数量为1*3*1
 * 然而实际上[3,1,5,8]在戳破 1最后变为了[3,5,8],戳破3时，应该获得硬币的最大数量为1*3*5，这个5就刚好是
 * 子问题[5,8]内的气球，也就是说子问题[3]必须依赖于子问题[5,8]才能正确计算硬币的最大数量
 * 那么如何解决这种依赖问题呢？
 * 这里应该采用逆向思维的方法，首先产生这种依赖性问题的根源是我们先选择了1并戳破了它
 * 那么就导致了[3]和[5,8]进行了靠拢产生了边界依赖（3依赖5）（可以想象火车中间去掉某一个车厢后，左右车厢的合拢）
 * 那么我们不要先戳破它（1）是不是可以解决问题？
 * 1、在开区间(i,j)之间戳气球k，先将(i,k)和(k,j)之间的气球戳破，再将k这个气球戳破；
 * 2、在这期间，每一个状态即为不同的开区间边界，那么每一次的状态转移都是一个独立的子问题；
 * 3、所以改变输入的数组，将其头尾各添加一个元素1变为point[0...numsSize+1]，
 * 就将原问题改变为在开区间(0，n+1)之间戳破所有气球所得的最高分即为答案。
 * 4、步骤
 * dp数组含义：dp[i][j]即为戳破开区间(i,j)内的所有气球所得的最高分；
 * 状态：即每一次开区间的边界i和j。
 * 状态转移方程：dp[i][j] = dp[i][k] + dp[k][j] + points[i] · points[k] · points[j];
 * @link https://leetcode-cn.com/problems/burst-balloons/solution/312-chuo-qi-qiu-by-dyzahng-khsp/
 * @限制:
 * @Level:
 */
public class Solution1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = {3, 1, 5, 8};
        int res = new Solution1().maxCoins(nums);
        System.out.println(res);
    }

    public int maxCoins(int[] nums) {
        //给定四个球分别为[3,1,5,8],其实可以看做数组[1,3,1,5,8,1];
        int len = nums.length + 2;
        int[] newNums = new int[len];
        newNums[0] = 1;
        newNums[len - 1] = 1;
        System.arraycopy(nums, 0, newNums, 1, len - 2);
        int[][] dp = new int[len + 2][len + 2];
        /* 方向：从下往上、从左往右 */
        for (int i = nums.length; i >= 0; i--) {
            for (int j = i + 1; j < nums.length + 2; j++) {
                for (int k = i + 1; k < j; k++) {
                    int res = dp[i][k] + dp[k][j] + newNums[i] * newNums[k] * newNums[j];
                    dp[i][j] = Math.max(dp[i][j], res);
                }
            }
        }
        /* 结果就为开区间(0, len + 1)之间的dp值 */
        return dp[0][nums.length + 1];
    }
}
