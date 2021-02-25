package DP系列.区间DP.多边形三角剖分的最低得分;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/11 17:42
 * @Description:
 * 区间DP套路，枚举所有的区间和区间内的点，区间内的点和区间左右端点将整个区间又划分为两个已知的子区间，典型的区间dp
 * @Url: https://leetcode-cn.com/problems/minimum-score-triangulation-of-polygon/
 * https://leetcode-cn.com/problems/minimum-score-triangulation-of-polygon/solution/qu-jian-dong-tai-gui-hua-4ms-by-njyang-yang-yang/
 * @限制:
 * @Level:
 */
public class Solution {

    /**
     * 思路见附图
     * @param A
     * @return
     */
    public int minScoreTriangulation(int[] A) {
        int len=A.length;
        if (len==3)return A[0]*A[1]*A[2];
        int[][] dp=new int[len][len];
        /**
         * 这里倒序的原因是
         * 比如在计算dp[0][3]时，需要用到dp[0][1]和dp[1][3]、dp[0][2]和dp[2][3]，即在填表时需要将dp[0][3]
         * 左侧和下边的结果先计算好，所以用倒序可以完成这个要求。
         */
//        for (int i=len-3;i>=0;i--){
//            for (int j=i+2;j<len;j++){
//                System.out.println(i+" "+j);
//                dp[i][j]=Integer.MAX_VALUE;
//                for (int m=i+1;m<j;m++){
//                    dp[i][j]=Math.min(dp[i][j],dp[i][m]+A[i]*A[j]*A[m]+dp[m][j]);
//                }
//            }
//        }
        /**
         * 区间DP做法
         */
        //一种长度对应多个起点的计算
        for (int step=2;step<len;step++){ // 枚举区间长度：step表示i和j的间隔，要构成三角形，至少间隔两个点
            for (int start=0;start<=len-3;start++){  //区间起点[0,len-3]
                int end=(start+step);  //区间末端,根据起点和长度得到终点
                if (end>=len)continue; //超过了len-1
                for (int m=start+1;m<end;m++){
                    if(dp[start][end]==0)
                        dp[start][end]= A[start] * A[end] * A[m] + dp[start][m] + dp[m][end];
                    else
                        dp[start][end]=Math.min(dp[start][end],dp[start][m]+A[start]*A[end]*A[m]+dp[m][end]);
                }

            }
        }
        return dp[0][len-1];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] A={3,7,4,5};
        int res=new Solution().minScoreTriangulation(A);
        System.out.println(res);
    }
}
