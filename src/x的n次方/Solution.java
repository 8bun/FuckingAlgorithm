package x的n次方;

/**
 * @description：
 * @url： https://leetcode-cn.com/problems/powx-n/
 * @限制：
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1]
 * n≤10^18 => O(logn)，最大公约数，时间复杂度
 * @author： Jack
 * @createTime： 2020/4/10 10:22
 * @level：
 */
public class Solution {
    //超时版本
//    public double myPow(double x, int n) {
//        double res=1;
//        if(n<0){
//            x=1.0/x;
//            n=-n;
//        }
//        for(int i=0;i<n;i++){
//            res*=x;
//        }
//        return res;
//    }
    //求x^n也就是求{x^(n/2)}^2
    public double myPow(double x, int n) {
        if(n<0){
            x=1.0/x;
            n=-n;
        }
        return fastPow(x,n);
    }

    private double fastPow(double x, int n) {
        if(n==0)
            return 1.0;
        double half=fastPow(x,n/2);
        if(n%2==0)
            return half*half;
        else return half*half*x;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().myPow(2.1,3));
    }
}
