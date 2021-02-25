package 把数字翻译成字符串;

/**
 * @description：使用动态规划求解
 * 从要求解的问题：求num的前len个数字的翻译种数，分解为子问题。
 * 对于一个数 num[i]，我们有两种选择：
 * 只翻译自己；
 * 和前面的数字组合翻译，前提是组合的数在 1−25 之间。
 * 用 dp(i)表示前 ii 个数字的翻译方法数。
 * 动态规划要求出解问题dp(i)和子问题dp(i-1)的关系（状态转移方程）：
 * 分析：
 * 基于以上两种选择，
 * ① 例如12345，如果不能组合，只能翻译自己，那么12345的翻译数(=1234的翻译数*5的翻译数)与1234的翻译数是一样的
 * 也就是dp(i)=dp(i-1)
 * ② 如果与前面的数字组合：
 * 如果能够组合，例如12325，2和5组合看成一个不可分割的整体（2、5此时只有一种翻译情况），
 * 那么12325的翻译数=
 * 123的翻译数*(25的翻译数=1)+(如果不采用组合，则等于只是翻译自己的情况)1232的翻译数*(5的翻译数=1)
 * 也就是dp(i)=dp(i-1)+dp(i-2)，前提是能够组合的情况
 * 这样就能写出状态转移方程了：
 * dp(i)={
 *    dp(i−2)+dp(i−1),num[i]和num[i−1]能合成一个字符
 *    dp(i−1),num[i]和num[i−1]不能合成一个字符
 * }
 * 再考虑边界条件：
 * dp(0)=dp(1)=1
 *
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/4 12:57
 * @level：
 */
public class Solution2 {
    public int translateNum(int num) {
        String num_str=String.valueOf(num);
        int len=num_str.length();
        if(len==1)
            return 1;
        //由于需要求到dp[len]，所以要开数组长度len+1
        int dp[]=new int[len+1];
        dp[0]=1;
        dp[1]=1;
        //由状态方程知道，dp[2]可以根据dp[0],dp[1]求出，所以从2开始
        for(int i=2;i<=len;i++){
            //dp[i]表示前i个，第i个字符在字符串中的下标为i-1，前一个字符为i-2
            //与前面组合>25或者前面为0，都表示不能组合
            if(num_str.substring(i-2,i).compareTo("25")>0||num_str.charAt(i-2)=='0'){
                dp[i]=dp[i-1];
            }
            else {
                dp[i]=dp[i-1]+dp[i-2];
            }
        }
        return dp[len];
    }

    public static void main(String[] args) {
        new Solution2().translateNum(25);
    }

}
