package DP系列.线性DP.其他经典问题.字符串匹配系列.通配符匹配;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/10 19:57
 * @Description:
 * @Url: https://leetcode-cn.com/problems/wildcard-matching/
 * @限制:
 * @Level:
 */
public class Solution {

    public boolean isMatch(String s, String p) {
        int lens=s.length();
        int lenp=p.length();

        boolean[][] dp=new boolean[lens+1][lenp+1];
        dp[0][0]=true;
        for (int i=1; i<=lenp; i++)
            dp[0][i]=dp[0][i-1]&&p.charAt(i-1)=='*';  //""->**..**,?不可以，因为它只能匹配单个"字符"
        for (int i=1;i<=lens;i++)
            dp[i][0]=false; //规则为"",s="aa"为false

        for (int i=1;i<=lens;i++){
            for (int j=1;j<=lenp;j++){
                //如ac,bac或ac,ba?
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                //如ac,ba*=>ac,ba(*当成"")或者a,ba*(*当成c,又因为*可以当成任意长度的，所以还保留*)
                else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[lens][lenp];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.next();
            String p = scanner.next();
            boolean res=new Solution().isMatch(s,p);
            System.out.println(res);
        }
    }
}
