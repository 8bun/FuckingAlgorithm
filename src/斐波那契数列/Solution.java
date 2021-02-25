package 斐波那契数列;

/**
 * @description: 使用递归会重复算很多，导致超时
 * @url:https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 * @author:Jack
 * @createTime:2020/2/21 17:00
 * @level:
 */
//超时版本
/*
public class Solutio2 {
    public int fib(int n) {
        if(n==0||n==1)
            return n;
        else return (fib(n-1)+fib(n-2))%1000000007;
    }
}*/
//我们可以使用数组保存每一步的fib(n)，这样后面要求就直接拿出来就好了
public class Solution {
    public int fib(int n) {
        int [] a=new int[n+1];
        for(int i=0;i<=n;i++){
            if(i==0||i==1)
                a[i]=i;
            else
                a[i]=(a[i-1]+a[i-2])%1000000007;
        }
        return a[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().fib(7));
    }
}
