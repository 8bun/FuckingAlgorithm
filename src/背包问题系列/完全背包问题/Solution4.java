package 背包问题系列.完全背包问题;

import java.util.Scanner;

/**
 * @description： 完全背包的一维优化，解释在硬币.背包最大价值问题.Solution3.Solution3()
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/7 9:20
 * @level：
 */
public class Solution4 {
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

        int dp[]=new int[weight_Of_Bag+1];

        for (int i=0;i<=weight_Of_Bag;i++){
            dp[i]=0;  //组成容量i的最大价值
        }
        for (int i=1;i<=num;i++) { //待装入的物品
            for (int j = w[i]; j <= weight_Of_Bag; j++) {
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
        }
        System.out.println(dp[weight_Of_Bag]);
    }
}
