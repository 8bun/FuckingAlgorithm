package DP系列.区间DP.统计不同回文子字符串;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/11 13:23
 * @Description:
 * 给定一个字符串 S，找出 S 中不同的非空回文子序列个数
 *
 * S = 'bccb'
 * 输出：6
 * 解释：
 * 6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。
 * 注意：'bcb' 虽然出现两次但仅计数一次。
 * @Url: https://leetcode-cn.com/problems/count-different-palindromic-subsequences/
 * @限制:
 * 字符串 S 的长度将在[1, 1000]范围内。
 * 每个字符 S[i] 将会是集合 {'a', 'b', 'c', 'd'} 中的某一个。
 * @Level:
 */
public class Solution {
    private static final int MOD = 1000000007;

    /**
     * 三维dp
     * @param S
     * @return
     */
    public int countPalindromicSubsequences(String S) {
        int len=S.length();
        //设dp[i][j][x] 为子串 S[i...j] 拥有不同回文子字符串的答案，其中x为0到3表示子序列以四种字符的某一种结尾的方案数
        int[][][] dp=new int[len][len][4];
        /*
          时间复杂度为O(n^2)，空间复杂度为O(n^2),忽略了的常数因子 4
         */
        for (int i=len-1;i>=0;i--){
            for (int j=i;j<len;j++){  //保证i<=j
                for (int k=0;k<4;k++){

                    char tailCh= (char) ('a'+k);
                    if (i==j){  //如果只有一个字符
                        if (S.charAt(j)==tailCh) dp[i][j][k]=1; //字符刚好是tailCh
                        else dp[i][j][k]=0;
                    }
                    else {//字符串长度>1
                        if (S.charAt(i)!=tailCh){ //不以tailCh为开头
                            //那么[i-1,...j-1]区间以tailCh为末尾的回文串个数等于
                            // [i-1,...j-2]区间为以tailCh为末尾的回文串个数
                            dp[i][j][k]=dp[i+1][j][k];
                        }
                        else if(S.charAt(j)!=tailCh){//不以tailCh为结尾
                            dp[i][j][k]=dp[i][j-1][k];
                        }
                        else { //以tailCh为开头并且以tailCh为末尾
                            if (i+1==j) {//区间长度为2，如aa,那么以a末尾的回文串为a,aa两个
                                dp[i][j][k]=2;
                            }
                            else {  //区间长度>2,需要计算排除首尾后的子串 S[i+1][j-1]中所有不同的回文（a、b、c、d 中的每一个）加上第一个和最后一个字符的两个回文
                                dp[i][j][k]=2;
                                //因为我们求的是[i-1,...j-1]为区间长度的在[j-1]位置为tailCh的回文串个数
                                //那么[i,...j-2]区间以什么字符为结尾的回文串只是它的内部串，也就是这个内部区间的回文串个数
                                //加上2就是以[i-1,...j-1]为区间长度的"在j-1位置为tailCh"的回文串个数
                                for (int m=0;m<4;m++){
                                    dp[i][j][k]+=dp[i+1][j-1][m];
                                    dp[i][j][k]%=MOD;
                                }
                            }
                        }
                    }
                }
            }
        }
        int cnt=0;
        for (int i=0;i<4;i++) {
            cnt+=dp[0][len-1][i];
            cnt%=MOD;
        }
        return cnt;
    }

    /**
     * 一维DP
     * @param S
     * @return
     */
    public int countPalindromicSubsequences1(String S) {
        int len=S.length();
        int[][] dp=new int[len][len]; //dp[i][j]表示的S[i, j]这段字符串中不同的回文子序列个数
        //初始化，单个长度的字符串也是一个结果
        for (int i = 0; i < len; ++i) {
            dp[i][i] = 1;
        }
        //for保证区间长度至少为2
        for (int i = len - 2; i >= 0; --i){
            for (int j = i + 1; j < len; ++j){
                //上面的两层for循环用于穷举区间[i, j]，i用于确定区间的起点，j确定区间的尾端，并且区间的长度都是由2逐渐增大
                if (S.charAt(i) == S.charAt(j)) {
                    //left用于寻找与S[i]相同的左端第一个下标，right用于寻找与S[i]相同的右端第一个下标
                    int left = i + 1, right = j - 1;
                    while (left <= right && S.charAt(left) != S.charAt(i)) {
                        ++left;
                    }
                    while (left <= right && S.charAt(right) != S.charAt(i)) {
                        --right;
                    }
                    if (left > right) {
                        //中间没有和S[i]相同的字母，例如"abca"这种情况
                        //其中dp[i + 1][j - 1]是中间部分的回文子序列个数，因为中间的每个子序列可以单独存在，也可以再外面包裹上字母a，所以每个
                        // 包含中间的每个子序列的回文串都有两种情况，所以要乘2。
                        //加2的原因是外层的"a"和"aa"也要统计上
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    }
                    else if (left == right) {
                        //中间只有一个和S[i]相同的字母，例如"aaa"这种情况，
                        //其中乘2的部分跟上面的原因相同，
                        //加1的原因是单个字母"a"的情况已经在中间部分算过了，外层就只能再加上个"aa"了。
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    }
                    else {
                        //中间至少有两个和S[i]相同的字母，例如"aabcdaa"这种情况，此时left指向1处的'a',right指向3处的'a'
                        //其中乘2的部分跟上面的原因相同，要减去left和right中间部分bcd的子序列个数的原因是
                        // dp[i + 1][j - 1] * 2这里把dp[left + 1][right - 1]计算了两遍，所以要减去一份。
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[left + 1][right - 1];
                    }
                }
                else {
                    //dp[i][j - 1] + dp[i + 1][j]这里计算了dp[i + 1][j - 1]两遍
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                }
                dp[i][j] = (dp[i][j] < 0) ? dp[i][j] + MOD : dp[i][j] % MOD;
            }
        }
        return dp[0][len - 1];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
