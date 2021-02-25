package DP系列.概率型DP.分汤;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/6/15 19:42
 * @Description:
 * 题目大概的意思是：
 * 有四组容量不同的A,B两个桶，A只能装水，B只能装油
 * 给定等量的水和油，问以每组的桶各自要求的最大容量对现有的水和油分配到A、B桶
 * 求水先分配完的概率 + 水和油同时分配完的概率 / 2。
 * 如果我们使用动态数组dp[i][j]表示：当给定i毫升的水和j毫升的油这个状态下的目标概率：水先分配完的概率 + 水和油同时分配完的概率 / 2
 * 从这个状态对每一组进行一次分配（概率是0.25）之后的状态记为下一个状态
 * 那么下一个状态下目标概率就为i,j各自减去每组分配后的i,j状态下的dp值，而dp[i][j]又是由下一个状态的所有情况组成的，每一种情况出现的
 * 概率为0.25，那么就可以得到dp[i][j] = 0.25 * (dp[i-100][j] + dp[i-75][j-25] + dp[i-50][j-50] + dp[i-75][j-25])
 * 由于四种分发基数都是25，即如果不足25按照25继续分，那么我们可以将N缩小为原来的25分之一
 * 得到dp[i][j] = 0.25 * (dp[i-4][j] + dp[i-3][j-1] + dp[i-2][j-2] + dp[i-3][j-1])
 * @Url: https://leetcode-cn.com/problems/soup-servings/
 * @限制: 
 * @Level: 
 */
public class Solution {

    public double soupServings(int N) {
        /**
         * N的范围是[0, 109]，这是一个特别大的数字了。
         * 并且提示中注明当我们返回的结果与真实误差小于10-6的时候，就算正确。
         * 而当N趋向于无穷大时，A先被分完以及A和B同时被分完的概率会无限接近于1。
         * 经过严格计算我们知道当N >= 4800之后，返回的概率值与1的差距就小于10-6了。
         * 所以当N >= 4800的时候，我们就直接返回1，否则会报内存超限
         */
        if(N>=4800)return 1;
        //假设起始容量为26，那么N=2，不可以浪费26/25的余数
        N = N / 25 + (N % 25 == 0 ? 0 : 1);
        double[][] dp = new double[N + 1][N + 1];
        Arrays.fill(dp[0], 1); //当i=0时，表示给定0毫升的水时，所有分组都同时水分配完，概率为0.25*(1+1+1+1)=1
        dp[0][0] = 0.5; //提供0毫升水、0毫升油，那么同时分配完的情况0.25*(1.0/2.0+1.0/2.0+1.0/2.0+1.0/2.0) = 0.5
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                //<0（已经分配完了，容量足够）的按照0来算
                dp[i][j]=0.25*(dp[Math.max(0,i-4)][j]+dp[Math.max(0,i-3)][Math.max(0,j-1)]+dp[Math.max(0,i-2)][Math.max(0,j-2)]+dp[Math.max(0,i-1)][Math.max(0,j-3)]);
            }
        }
        return dp[N][N];
    }
    public static void main(String[] args){
         Scanner scanner=new Scanner(System.in);
    }
}
