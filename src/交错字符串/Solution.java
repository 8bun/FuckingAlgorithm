package 交错字符串;

/**
 * @description： 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 * dp[i][j] = true表示s1[0, i],s2[0, j]可以交错组成s3[0, i+j]；
 * dp[0][0] = true
 * 假设s1=aab,s2=db,s3=aadbb，那么
 * 我们分析dp[2][2]的值，也就是说s3[aadb]是否由s1[aa]和s2[db]交错组成
 * 因为s3的第四个字符与s2的第2个字符相同，也就是s2[1]=s3[3]，把s3的b划掉，那么问题可转化为
 * s3的前3个字符是否是s1的前2个字符和s2的前1个字符的交错，这时，d与s2的d相同，那么把s3的d划掉，
 * 问题又转化为s3的前2个字符是否是s1的前2个字符和s2的前0个字符的交错。
 * 当然，上面只是s3末尾先跟s2前2个字符末尾相同的情况，跟s1相同也类似
 * 如果两者都相同，那么两种都试一下，有为true则可以为true
 * 所以写出状态转移方程dp[2][2] = dp[1][2] && s1[1]==s3[1+2] || dp[2][1]&&s2[1]==s3[1+2]
 * 即dp[i][j] = dp[i-1][j] && s1[i-1]==s3[i+j-1] || dp[i][j-1] && s2[j-1]==s3[i+j-1]
 * dp[0][0]表示前0个字符""和""能否组成"",当然为true
 * 为了防止i>=1时，j=0的情况造成方程的错误，j-1<0，那么要先规定好这种情况，只走一边即可
 * j>=1，i=0也是类似
 * @url： https://leetcode-cn.com/problems/interleaving-string/
 * @限制：
 * @author：Jack
 * @createTime：2020/3/13 11:36
 * @level：
 */
public class Solution {

    public boolean isInterleave(String s1, String s2, String s3) {
        int m=s1.length();
        int n=s2.length();
        if (s3.length() != m + n) {
            return false;
        }
        boolean dp[][]=new boolean[m+1][n+1];
        dp[0][0]=true;
        for(int i=1;i<=m;i++)
            dp[i][0]=dp[i-1][0] && s1.charAt(i-1)==s3.charAt(i-1);
        for(int j=1;j<=n;j++)
            dp[0][j]=dp[0][j-1] && s2.charAt(j-1)==s3.charAt(j-1);
        for(int i=1;i<=m;i++)
            for(int j=1;j<=n;j++)
                dp[i][j]=(dp[i][j-1]&&s2.charAt(j-1) == s3.charAt(i+j-1))||( dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1));
        return dp[m][n];
    }
}
