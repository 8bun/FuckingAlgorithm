package 背包问题系列.背包01问题;

import java.util.Scanner;

/**
 * @description：
 * 01背包问题的一维dp
 * @url： 分析思路来源：https://blog.csdn.net/yandaoqiusheng/article/details/84929357
 * https://www.luogu.com.cn/blog/RPdreamer/post-01-bei-bao-yu-wan-quan-bei-bao-di-mei-ju-shun-xu-di-fou-tong-chu
 * @限制：
 * @author：Jack
 * @createTime：2020/4/6 13:36
 * @level：
 */
public class Solution2 {
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
            dp[i]=0;  //组成容量i的最大价值初始化为0(没有选择任何物体)
        }

        //填表
        for (int i=1;i<=num;i++){ //待装入的物品
            //经过每一次的i循环，dp[j](j的范围如下)都是被上次i循环进行更新的。
            //如果现在经历的是第i次循环，那么这次的 dp[j]=Math.max(dp[j],dp[j-w[i]]+v[i]);
            //这条语句还没执行前，所有的dp[j]值都是第i-1次循环的结果。
            //dp[j]此时相当于dp[i-1][j]，dp[j-w[i]]相当于dp[i-1][j-w[i]]的含义
            //所以如果现在经历的是第i次循环，那么在此次循环开始之前，dp[j]表示前面的第i-1次循环（经过了总共i-1次循环后）后
            //涉及的物体是w[1]...w[i-1]，当背包最大的容量为j时(还没装物体)，选择这些i-1个物体装入这个j容量的背包的最大价值；
            //在此次循环开始之后，dp[j]表示当背包最大的容量为j时(还没装物体),选择w[1]...w[i]这些i个物体装入这个j容量的背包的最大价值
            //所以我们发现，当i执行到最后一层循环i==num后，此时dp[weight_Of_Bag]就表示当背包最大的容量为weight_Of_Bag时，选择w[1]...w[num]
            //这些i个物体装入这个weight_Of_Bag容量的背包的最大价值
            //来看代码，dp[j]=Math.max(dp[j],dp[j-w[i]]+v[i])，此时在执行第i次循环，
            //我们为了让dp[j]和dp[j-w[i]]都是第i-1次循环后的结果，两个值的含义根据上面的分析应该分别是
            //①dp[j]表示当背包最大的容量为j时(还没装物体),选择w[1]...w[i-1]这些i-1个物体装入这个j容量的背包的最大价值
            //②dp[j-w[i]]表示当背包最大的容量为j-w[i]时(还没装物体),选择w[1]...w[i-1]这些i-1个物体装入这个j-w[i]容量的背包的最大价值
            //而我们知道，这两个值的数组下标一定是j>j-w[i]的，
            //如果我们的j是顺序从小到大遍历的，那么我们在j这个第二层循环体中，每执行完一个j，我们都是更新dp数组中小的下标的值
            //这个时候，例如，执行第i次循环内的第1次j，假设此时计算dp[1]=max(dp[1],dp[0]+1);
            //那么该第1次j执行过后，dp[1]的值就是第i次循环后的结果了，而在第2次j执行前，
            //假设此时计算dp[2]=max(dp[2],dp[1]+1);由于此时的dp[1]已经是第i次循环后的结果了，而我们
            //要求的是无论在任何情况下，①②的条件是满足的，此时已经破坏了我们的条件，即执行第i次循环且背包容量为j时，
            // 此时的f[j]存储的是f[i−1][j]，此时f[j−w[i]]存储的是f[i][j−w[i]]；
            //可能会导致什么结果呢，回到前面的假设，在计算dp[1]=max(dp[1],dp[0]+1);后，如果dp[0]+1是更大的，那么当w[i]=1放入的时候才能实现最大的价值化，
            //下次j，假设计算dp[2]=max(dp[2],dp[1]+1); 因为此时一直处于第i次循环，所以w[i]=1一直不变，如果dp[1]+1是还更大的，那么当w[i]=1放入的时候才能实现最大的价值化，
            //也就是说我们把w[i]加了不止1次，而01背包问题的物体装入是至多一个而已的，也就出现了错误。
            //再来分析从大到小逆序遍历j的情况，
            //同样的分析方法，执行第i次循环内的第1次j，假设此时计算dp[20]=max(dp[20],dp[12]+1);
            //那么该第1次j执行过后，dp[20]的值就是第i次循环后的结果了，在第2次j执行前，因为j是递减的嫲，
            //假设此时计算dp[19]=max(dp[19],dp[13]+1)，此时dp[19]的值就是第i次循环后的结果了，
            //所以其实在这种情况下，第i次的循环内任何j不会出现①②条件不满足的情况。分析完毕
            //
            for (int j=weight_Of_Bag;j>=w[i];j--){  //倒序，模拟多个背包，每次保证w[i]可以放入容量j的背包
                //如果放不下
                //原版，可优化
//                if(w[i]>j){//放到for条件上
////                    dp[j]=dp[j]; //可去
//                }else {
//                    dp[j]=Math.max(dp[j],dp[j-w[i]]+v[i]);
//                }
                dp[j]=Math.max(dp[j],dp[j-w[i]]+v[i]);
            }
        }
        System.out.println(dp[weight_Of_Bag]);
    }
}
