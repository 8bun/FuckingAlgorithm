package DP系列.线性DP.其他经典问题.字符串匹配系列.编辑距离;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/10 16:49
 * @Description:
 * @Url: https://leetcode-cn.com/problems/edit-distance/
 * @限制:
 * @Level:
 */
public class Solution {

    public int minDistance(String word1, String word2) {
        int len1=word1.length();
        int len2=word2.length();
        int[][] dp=new int[len1+1][len2+1]; //dp[i][j]表示word1[0]..word1[i-1]转换成word2[0]..word2[j-1]需要的最少步数
        dp[0][0]=0;
        for (int i=1;i<=len2;i++)
            dp[0][i]=i;
        for (int i=1;i<=len1;i++)
            dp[i][0]=i;
        for (int i=1;i<=len1;i++){
            for (int j=1;j<=len2;j++){
                /**
                 # 本来就相等，不需要任何操作
                 # text1[0..i] 和 text2[0..j] 的最小编辑距离等于
                 # text1[0..i-1] 和 text2[0..j-1] 的最小编辑距离
                 */
                if (word1.charAt(i-1)==word2.charAt(j-1)) //相同字母，不用操作
                    dp[i][j]=dp[i-1][j-1];
                else { //不同字母，三种选择,
                    /**
                     * cda
                     * cde
                     * =>
                     * 当
                     * i=3,j=3时，计算cda->cde所需的最少方案数，此时text1[2]=a，text2[2]=e,a!=e
                     * 可以有三种选择:
                     * ①删去a，为"cd"->cde的方案数+1,即dp[i-1][j]+1
                     * ②替换a为e，为"cd"->"cd"的方案数+1,即dp[i-1][j-1]+1
                     * ③在a后插入e，为"cdae"->"cde"的方案数+1,又因为此时它们的末尾相同，所以又回到了前面相同的处理方法，所以
                     * 不用处理，所以又转换为"cda"->"cd"的方案数+1,即dp[i][j-1]+1
                     * 总的来说，①是最省事的，其他都有一些多余的操作
                     * 最后取这三种选择的最小方案数即可
                     */
                    dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i-1][j-1],dp[i][j-1]))+1;
                }
            }
        }
        return dp[len1][len2];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
