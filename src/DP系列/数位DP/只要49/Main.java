package DP系列.数位DP.只要49;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/27 09:26
 * @Description:
 * 给你一个N,求出[1,N]内的数字中包含49的数字个数
 * From 1 to 500, the numbers that include the sub-sequence "49" are "49","149","249","349","449","490","491","492","493","494","495","496","497","498","499",
 * so the answer is 15.
 * 由于是统计有49的数字，那么就可以统计所有没有49的数字个数，用N减去即可；
 *
 * 我们用数位dp（dfs+记忆化搜索）的做法来统计没有49的数字个数(数位dp其实就是优化正常数数的过程，而正常数数的过程其实就是dfs的过程)
 * 根据N的位数，遍历每一位上数字构成新数，例如N=5123
 * 我们用一个limit来确定当前位的所有之前的位上是不是都是到达了5123对应位上的上限数字(5 1 2 3)
 * 如果是的话那么当前位就只能选择5123在当前位的对应位上的上限数字，否则可以选择0-9,才能保证不越上界5123
 *
 * 在合法的选择范围内，如果i=3时，选了0，那么i=2时可以选择0-9，也就是有10种不包含49的，
 * 后面的i=3可以选择10种数字在合法范围内，但是i=2是如果选了4，那么低位就只能有0-8种选择，因为49不是我们要统计的
 * 我们发现，i=2和i=3能选择0-9表示了它们的limit=false，并且
 * 我们可以定义一个if4状态来判断当前位是否选择了4，例如i=2时如果没有选4,并且limit=false，
 * 例如选了0,1,2,3,5,6,7,8,9时，那么我们在遍历到0的时候，算10次，在遍历到1的时候，直接返回遍历0时当前位到低位统计的
 * 不包含49的个数，我们发现它们都不是选了4这个状态，则可以用一个if4状态来判断当前位是否选择了4
 * 如果没有选4，并且下一位可以选择0-9那么就可以对返回之前统计的同样状态下的数量
 * 另一个例子，在第1,2位分别选了5和1后，limit=true，那么i=2时只能在0-2选择，如果选了0，那么低位时
 * limit=false,低位可以选择0-9，那么就记忆起来；如果选了1，那么低位只能选择0-3，就要分别计算
 * 0 1 2 3 4 [5] 6 7 8 9 (高位)
 * 0 [1] 2 3 4 5 6 7 8 9 (i=3)
 * 0 1 [2] 3 4 5 6 7 8 9 (i=2)
 * 0 1 2 [3] 4 5 6 7 8 9 (低位)
 * @Url: http://acm.hdu.edu.cn/showproblem.php?pid=3555
 * https://www.bilibili.com/video/BV1ss411E7vq/?p=2&t=2
 * @限制:
 * 1 <= N <= 2^63 - 1
 * @Level:
 */
public class Main {
    //2^63:9223372036854775807有19位数
    private static long[] digit = new long[20];
    private static long[][] dp = new long[20][2];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- != 0) {
            long n = scanner.nextLong();
            for(long[] x : dp) Arrays.fill(x, -1 );
            //solve(n)统计的是[0,n]有多少个数字不包含49,n+1使得区间补了一个0，算出的才是[0,n]有多少个数字包含49，也就是[1,n]有多少个数字包含49
            System.out.println(n + 1 - solve(n));
        }
    }

    /**
     * 统计[0,num]有多少个数字不包含49
     * @param num
     * @return
     */
    private static long solve(long num) {
        int k = 0; //记录有多少数位
        while (num != 0){
            digit[++k] = num % 10;
            num /= 10;
        }
        return dfs(k, 0, true); //最高位一定要限制
    }

    /**
     *
     * @param curPos 当前位的下标, 低位下标低，从1开始
     * @param ifLastSelect4 上一位是否选了4
     * @param curLimit 当前位是否有0-9 10种选择，limit=false，是由上一位的
     *                 limit经过&之后传下来的，false表示可能上一位就有0-9种选择，也可能是选择了上界以内的数字
     *              例如num=5123, 当前位为最低位时，limit=false时，前面的选择可能是 4 9 3 _ 或 选择 5 1 0 _
     * @return
     */
    private static long dfs(int curPos, int ifLastSelect4, boolean curLimit) {
        /**
         * 为什么pos等于零了，我们就可以返回1？
         * 我们的dfs每一次搜索，都是在保证合法的情况下
         * 如果某种情况不合法，是根本不会进入到pos为零这个阶段
         */
        if (curPos == 0) return 1;
        //例如num=5123, 当前位为最低位时，limit=false时，前面的选择可能是 4 9 3 _ 或 选择 5 1 0 _
        //如果当前位有0-9种选择，并且当前位到最低位已经统计过多少数字不包含49，那么就可以直接返回
        //例如num=5123, 当前位为最高位时，ifLastSelect4=0，当前位选了0-3（不越界），那么在选择0后，统计0___中
        //不包含49的数字个数为a并记录起来；如果后面，高位选择了1，那么1___中不包含49的数字个数也是a
        //所以就可以直接返回a了
        //如果高位选择4的话，那么就不能直接返回a，因为下一位如果选了9就包含49了
        //也就是说dfs(最高位),选择了4->dfs(下一位),ifLastSelect4=1,那么就得重新计算一遍，剪去9的支即可
        if (!curLimit && dp[curPos][ifLastSelect4] != -1)
            return dp[curPos][ifLastSelect4];

        long up_bound = curLimit ? digit[curPos] : 9;
        long cnt = 0; //统计当前位在[0,up_bound]内所有选择情况下不包含49的数字个数

        for (int curSelect = 0; curSelect <= up_bound; curSelect++){
            if (ifLastSelect4 == 1 && curSelect == 9) continue; //剪去49这一支
            cnt += dfs(curPos - 1, curSelect == 4 ? 1 : 0, curLimit && curSelect == up_bound);
        }

        /**
         * curLimit=true的情况，为什么不需要记忆？
         * 如num=5123, 当前位为最高位的下一位时，如果上一位选了5时, 就不用记录5___中不包含49的数字个数
         * 因为后面不会再有6了(6___)，也就是说没有情况数和它一样的数字表示（因为它的下一位只能选择0-1），不会
         * 导致重复计算的问题，所以没必要去记忆了
         */
        if (!curLimit) dp[curPos][ifLastSelect4] = cnt;
        return cnt;
    }
}
