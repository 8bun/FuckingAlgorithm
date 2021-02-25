package 背包问题系列.背包01问题a;

import java.util.Scanner;

/**
 * @description：
 * 01背包问题：
 * 给定n种物品和一背包。物品i的重量是w[i]>0，其价值为v[i]>0，背包的容量为c。
 * 问应如何选择装入背包中的物品（每种物品只有一件），使得装入背包中物品的总价值最大？
 * 动态规划自顶向下求，
 * 设dp[i][j]表示在只有w[1]到w[i]总共i个物品可选择（每种至多装入1个）的情况下，背包容量剩余j时的最大价值
 * 第w[i]个物品可以选择放进背包或者不放进背包（这也就是0和1）
 * ①如果放得下，并且选择放入背包
 * 那么dp[i][j]=dp[i-1][j-w[i]]+value[i] //少了一种选择，多了一种价值(注意这里跟硬币问题是有不同的，
 * 硬币问题是放入一个还可以放入，相当于完全背包问题)
 * ②如果放得下，并且选择不放入背包
 * 那么dp[i][j]=dp[i-1][j]
 * 然后取它们的最大价值，作为dp[i][j]的值
 * ③如果放不下，那跟②的情况一样
 * 样例：
 * 5 20
 * 2 3
 * 3 4
 * 4 5
 * 5 8
 * 9 10
 * 结果：26
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/6 12:11
 * @level：
 */
public class Solution {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int num=scanner.nextInt(); //物品种类
        int weight_Of_Bag=scanner.nextInt(); //背包容量
        int[] w=new int[num+1];  //物品重量
        int[] v=new int[num+1];  //物品价值
        for (int i=1;i<=num;i++){
            w[i]=scanner.nextInt();
            v[i]=scanner.nextInt();
        }

        int dp[][]=new int[num+1][weight_Of_Bag+1];

        for (int i=0;i<=weight_Of_Bag;i++){
            dp[0][i]=0;  //有0种物品选择，组成容量i的最大价值
        }

        //填表
        for (int i=1;i<=num;i++){ //待装入的物品
            for (int j=1;j<=weight_Of_Bag;j++){  //剩余容量
                //如果放不下
                //可见，dp[i][*]只与dp[i-1][*] 的状态有关，都是和
                //也就是说填表过程中，我们第i行的数据只是与它的上一行的数据有关，其它行都没有用，可以去掉
                //那么万一数据太大，我们就会造成不必要的空间浪费，所以可以使用一维数组来优化
                //我们定义dp[i]表示背包剩余容量为i时，在w[1]，w[num]重的（所有）物体中选择装包的最大价值
                if(w[i]>j){
                    dp[i][j]=dp[i-1][j];
                }else {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-w[i]]+v[i]);
                }
            }
        }
        System.out.println(dp[num][weight_Of_Bag]);
    }
}
