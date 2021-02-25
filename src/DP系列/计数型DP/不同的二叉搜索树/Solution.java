package DP系列.计数型DP.不同的二叉搜索树;
import java.util.Scanner;
/**
 * @Author: Jack
 * @Date: 2020/6/10 17:44
 * @Description: 
 * @Url:
 *
 * 设dp[num] 表示以num为根结点的二叉搜索树的数目，则其左子树节点个数为num-1个，右子树节点为n-num个
 * 所以题目求的总共方案sum[n] = dp[1]+dp[2]+...dp[n]
 * 对于左子树个数为num-1，其方案数为sum[num-1]，右子树个数为n-num，其方案数为sum[n-num]，则dp[num]=sum[num-1]*sum[n-num]
 * 所以可以带入sum[n]方程求得sum[n] = sum[0]*sum[n-1] + sum[1]*sum[n-2] + .. + sum[n-1]*sum[0]
 * 也就是sum[n]为两个index的和为n-1的sum的乘积的和
 * 此方程被称为卡特兰数公式
 *
 * @限制: 
 * @Level: 
 */
public class Solution {

    public int numTrees(int n) {
        int[] sum = new int[n + 1];
        //初始值为1，保证乘积有意义
        sum[0] = 1;
        sum[1] = 1;

        for (int i = 2; i <= n; i++) { //sum[i] = sum[0]*sum[i-1] + sum[1]*sum[i-2] + .. + sum[i-1]*sum[0]
            for (int j = 0; j <= i - 1; j++) {
                sum[i] += sum[j] * sum[i - 1 - j];
            }
        }
        return sum[n];
    }
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
   }
}
