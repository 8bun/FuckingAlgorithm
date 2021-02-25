package 背包问题系列.应用.硬币找零总方案数;

/**
 * @description：
 * coin[]数组表示几种币值
 * dp[i][j]表示由coin[0]到coin[i]这几种coin能构成总币值j的情况数
 * ，包括了取一个coin[i]和不取coin[i]进行组合的情况
 * ① 取coin[i]，总情况数是dp[i][j-coin[i]]，这里需要注意，这个式子
 * 表示由coin[0]到coin[i]这几种coin能构成总币值j-coin[i]的情况数，要
 * 构成j，那么至少要加上一个coin[j]，coin[0]到coin[i]这几种coin中还是包括了coin[j]可选
 * 而这个不管有没有选，还是保证了coin[i]被选了。
 * 也就是，c[i, j-coin[i]] 表示，coin[i]硬币还可以继续使用。因为第一个参数还是i
 *② 不取coin[i]来组合，那么总情况数是dp[i-1][j]
 * @url： https://leetcode-cn.com/problems/coin-lcci/
 * 详解解读 https://recomm.cnblogs.com/blogpost/5578852
 * @限制：
 * @author：Jack
 * @createTime：2020/4/5 18:19
 * @level：
 */
public class Solution1 {

    public int waysToChange(int n) {
        int[] coin={1,5,10,25};
        int len=coin.length;
        int[][] dp=new int[len][n+1];
        for (int i=0;i<len;i++)
            dp[i][0]=1; //所有coin都是0个，一种情况
        for (int i=1;i<=n;i++)
            dp[0][i]=1;  //第一种硬币1元组成i，一种情况（结合题目，有的时候不是）
        for (int i=1;i<len;i++){
            for (int j=1;j<=n;j++){
                if(j>=coin[i])
                    dp[i][j]=dp[i][j-coin[i]]%1000000007+dp[i-1][j]%1000000007;
                else
                    dp[i][j]=dp[i-1][j]%1000000007;
            }
        }
        return dp[len-1][n]%1000000007;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().waysToChange(6));
    }
}
