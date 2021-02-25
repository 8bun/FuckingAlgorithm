package 背包问题系列.完全背包问题b;

import java.util.Scanner;

/**
 * @description： 将01背包问题中物体加入数量规定为不止一个，求最大价值，为完全背包问题
 * 仿照01背包的思路，
 * 设dp[i][j]表示在只有w[1]到w[i]总共i个物品可选择（每种至少装入0个）的情况下，背包容量剩余j时的最大价值
 * 第w[i]个物品有k个(k>=0，并且这k个w[i]的总重量是不超过背包剩余容量j的)，可以选择放进背包或者不放进背包，
 * ①如果放得下k个w[i]，并且选择放入背包
 * 那么dp[i][j]=dp[i-1][j-k*w[i]]+k*value[i] //少了一种选择，多了k个w[i]的价值
 * ②如果放得下k个w[i]，并且选择不放入背包
 * 那么dp[i][j]=dp[i-1][j]
 * 然后取它们的最大价值，作为dp[i][j]的值
 * ③如果放不下k个w[i]，
 * 跟②一样
 * 总结：
 * dp[i][j]=Max(dp[i-1][j],dp[i][j]=dp[i-1][j-0*w[i]]+0*value[i],dp[i][j]=dp[i-1][j-1*w[i]]+1*value[i],.....)
 * 优化，Max(dp[i-1][j], dp[i-1][j], dp[i-1][j-w[i]]+value[i], dp[i-1][j-2w[i]]+2value[i],....)
 * =>Max(dp[i-1][j], dp[i-1][j-w[i]]+value[i], dp[i-1][j-2w[i]]+2value[i],....)
 * =>Max(dp[i-1][j-k*w[i]]+k*value[i])
 * =>i,j固定，从k=0开始遍历，
 * =>第一次dp[i][j]=Max(dp[i][j],dp[i-1][j])取最大值
 * =>第二次k=1,dp[i][j]=Max(dp[i][j](上面k=0时式子和之前的dp[i][j]的最大值)，dp[i-1][j-w[i]]+value[i])
 * 比较k=1和k=0时式子和之前的dp[i][j]的最大值，更新给新的dp[i][j]
 * 所以Max(dp[i-1][j-k*w[i]]+k*value[i])=>
 *  dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k*w[i]] + k*v[i]);
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/6 22:10
 * @level：
 */
public class Solution3 {
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

        long dp[][]=new long[num+1][weight_Of_Bag+1];

        for (int i=0;i<=weight_Of_Bag;i++){
            dp[0][i]=0;  //有0种物品选择，组成容量i的最大价值
        }

        //填表
        for (int i=1;i<=num;i++){ //待装入的物品
            for (int j=1;j<=weight_Of_Bag;j++){  //剩余容量
                for (int k=0;k*w[i]<=j;k++) {
                    //dp[i][j] = Math.max(dp[i-1][j], dp[i - 1][j - k*w[i]] + k*v[i]);
                    //中其实dp[i-1][j]可以看成dp[i - 1][j - k*w[i]] + k*v[i]中k=0的情况
                     dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k*w[i]] + k*v[i]);
                    //还可以继续优化
                    //根据上式,dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i]] + v[i], dp[i - 1][j - 2w[i]] + 2v[i], dp[i - 1][j - 3w[i]] + 3v[i],...... );
                    //dp[i][j-v[i]]=Math.max(dp[i][j-v[i]], dp[i - 1][j-v[i] - w[i]] + v[i], dp[i - 1][j-v[i] - 2w[i]] + 2v[i], dp[i - 1][j-v[i] - 3w[i]] + 3v[i],...... );

               }
            }
        }
        System.out.println(dp[num][weight_Of_Bag]);
    }
    //根据上式,①dp[i][j] = Math.max( dp[i - 1][j], dp[i - 1][j - w[i]] + v[i], dp[i - 1][j - 2w[i]] + 2v[i], dp[i - 1][j - 3w[i]] + 3v[i],...... );
    // dp[i][j-w[i]]=Math.max( dp[i - 1][j-w[i]], dp[i - 1][j- w[i] - w[i]] + v[i], dp[i - 1][j-w[i] - 2w[i]] + 2v[i], dp[i - 1][j-w[i] - 3w[i]] + 3v[i],...... );
    //=>②dp[i][j-w[i]]=Math.max( dp[i - 1][j-w[i]], dp[i - 1][j- 2w[i]] + v[i], dp[i - 1][j- 3w[i]] + 2v[i], dp[i - 1][j-4w[i]] + 3v[i],...... );
    //根据①②，①的第二项和②的第一项比较，有这样的关系:①的第二项=②的第一项+v[i]，也就是说
    //①从第二项开始，就一直包含了②的对应项（从第一项开始）
    //所以③dp[i][j]=Max(dp[i][j], dp[i][j-w[i]]+v[i])
    //再根据①，②的推导，都加入③中，对③进行验证
    //dp[i][j]=Max( dp[i - 1][j], dp[i - 1][j - w[i]] + v[i], dp[i - 1][j - 2w[i]] + 2v[i], dp[i - 1][j - 3w[i]] + 3v[i],......，dp[i - 1][j-w[i]]+v[i], dp[i - 1][j- 2w[i]] + v[i]+v[i], dp[i - 1][j- 3w[i]] + 2v[i]+v[i, dp[i - 1][j-4w[i]] + 3v[i]+v[i],......）
    //=>去重后得到④dp[i][j]=Max( dp[i - 1][j], dp[i - 1][j-w[i]]+v[i], dp[i - 1][j- 2w[i]]+2v[i], dp[i - 1][j- 3w[i]+3v[i], dp[i - 1][j-4w[i]]+4v[i],......）
    //④右边相比于①右边完全相同，也就是等式成立，这里是要首先考虑装不选w[i]（不管装不装得下）的情况，也就是dp[i - 1][j]的情况，等式才可成立，所以循环要加上
    //或者从理解其表达的意义上来讲，我们可以参照 硬币.背包最大价值问题.应用.硬币找零总方案数.Solution1中
    // dp[i][j]=dp[i][j-coin[i]]的解释(两个解决的方案有点类似，但是一个是求方案数，一个是最大价值)
    private void Solution2(int num,int dp[][],int weight_Of_Bag,int v[],int w[]){
        for (int i=1;i<=num;i++) { //待装入的物品
            for (int j = 1; j <= weight_Of_Bag; j++) {  //剩余容量
                dp[i][j] = dp[i - 1][j];
                if(w[i]<=j)
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - w[i]] + v[i]);
            }
        }
        System.out.println(dp[num][weight_Of_Bag]);
    }

    /**
     * 一维优化,代码见 硬币.背包最大价值问题.完全背包问题.Solution4
     * @param num
     * @param dp
     * @param weight_Of_Bag
     * @param v
     * @param w
     * @url
     */
    private void Solution3(int num,int dp[],int weight_Of_Bag,int v[],int w[]){
        for (int i=1;i<=num;i++) { //待装入的物品
            //for (int j = 1; j <= weight_Of_Bag; j++) {  //剩余容量
//                dp[i][j] = dp[i - 1][j];
//                if(w[i]<=j)
//                    dp[i][j] = Math.max(dp[i][j], dp[i][j - w[i]] + v[i]);
                //删去第一维
//                dp[j] = dp[j]; //无意义
//                if(w[i]<=j) //让内层循环从w[i]开始
//                    dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            for (int j = w[i]; j <= weight_Of_Bag; j++) {  //正序，因为右边取的都是i的状态，跟01的一维逆序取i-1状态是不同的
//                dp[i][j] = Math.max(dp[i][j], dp[i][j - w[i]] + v[i]);
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
        }
        System.out.println(dp[weight_Of_Bag]);
    }
}
