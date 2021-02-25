package 数值的整数次方;

/**
 * @description:
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 * @url: https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
 * @author: Jack
 * @createTime: 2020/2/24 10:00
 * @level: 中等
 */
public class Solution {

    public double myPow(double x, int n) {
        if(n==0)
            return 1;
        if(n==1)
            return x;
        if(n==-1)
            return 1/x;
        double half=myPow(x,n/2);   //降（半）幂求法
        double mod=myPow(x,n%2);  //mod=1或者x
        return half*half*mod;
    }
}
