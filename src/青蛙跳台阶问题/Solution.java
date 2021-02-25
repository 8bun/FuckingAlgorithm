package 青蛙跳台阶问题;

/**
 * @description:斐波那契问题变种
 * @url:
 * https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
 * @author:Jack
 * @createTime:2020/2/21 19:18
 * @level:
 */
public class Solution {
    public int numWays(int n) {
        int [] a=new int[n+1];
        a[0]=1;
        for(int i=1;i<=n;i++){
            if(i==1||i==2)
                a[i]=i;
            else
                a[i]=(a[i-1]+a[i-2])%1000000007;
        }
        return a[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numWays(7));
    }
}
