package n个整数组成sum的方案;

import java.util.Scanner;

/**
 * @description：
 * 动态规划分析：dp[i][j]表示前i个数能组合成和j的方案数：
 * 前i个数包括了a[i-1],我们分析a[i-1]之前的i-1个数的组合情况
 * 那么a[i-1]之前的i-1个数能组合成和j的方案数，要看a[i-1]与j的大小
 * 如果a[i-1]>j，那么a[i-1]就不能与前i-1个数再进行组合了，因为它自己本来就比j大，组合了就更大了
 * 那么，此时方案数为dp[i-1][j]；
 * 如果a[i-1]<=j，那么，dp[i][j]的所有情况包括：a[i-1]的前i-1个数组合成j的方案数+a[i-1]的前i-1个数组合成j-a[i-1]的方案数
 * 所以能写出动态转换方程：
 * dp[i][j]=dp[i-1][j]（a[i-1]>j）
 * dp[i][j]=dp[i-1][j]+dp[i-1][j-a[i-1]]（a[i-1]<=j）
 * 当然当i=0，j!=0时,为0，无法使用0个元素组合成j
 * 当j=0，i>=0时,为1，可以使用0个元素组成0，那么就是1种方案
 * @url：https://www.nowcoder.com/questionTerminal/7f24eb7266ce4b0792ce8721d6259800
 * @限制：
 * @author：Jack
 * @createTime：2020/3/16 11:28
 * @level：
 */
public class Solution {
    //给定一个有n个正整数的数组A和一个整数sum,求选择数组A中部分数字和为sum的方案数。
    //当两种选取方案有一个数字的下标不一样,我们就认为是不同的组成方案。
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            int n=scanner.nextInt();
            int sum=scanner.nextInt();
            int[] A=new int[n];
            for(int i=0;i<n;i++)
                A[i]=scanner.nextInt();
            long[][] dp=new long[n+1][sum+1];  //dp[i][j]表示用前i个值组成和为j的方案个数
            //前i个组成和为0的方案只有1种，即什么都不选
            for(int i=0;i<=n;i++){
                dp[i][0]=1;
            }
            //用0个元素不能组成1-sum
            for(int j=1;j<=sum;j++){
                dp[0][j]=0;
            }

            for(int i=1;i<=n;i++){
                for(int j=0;j<=sum;j++){
                    if(A[i-1]<=j){
                        dp[i][j]=dp[i-1][j]+dp[i-1][j-A[i-1]];
                    }else{
                        dp[i][j]=dp[i-1][j];
                    }
                }
            }
            System.out.println(dp[n][sum]);
        }
    }

}
