package 剪绳子II;

import java.math.BigInteger;

/**
 * @description:面临的问题是求a的num_3次方，其中num_3<=333，将其求出会发生溢出
 * 对于求余操作，有如下规则：(xy)⊙p=[(x⊙p)(y⊙p)]⊙p
 * 本题中，由于本题中 x<p，∴ x%p=x
 * 也就是说x^a%p==((x^(a-1)%p)*x)%p
 * 例如，如果求x^5%p,那么可从x^0%p==1开始求，再根据上一个结果求出x^1%p=(（x^0%p）*x)%p的值
 *
 * 对于x^a情况，则拆分为a个(x%p)相乘，最后再%p可得出
 * @url:https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/solution/mian-shi-ti-14-ii-jian-sheng-zi-iitan-xin-er-fen-f/
 * @author:Jack
 * @createTime:2020/2/23 13:02
 * @level:
 */
public class Solution {

    public int cuttingRope(int n) {
        //n=2,1*1,n=3,1*2
        if (n <= 3)
            return n - 1;
        int num = 1000000007;
        long res=1;  //乘积

        while (n>4){
            res*=3;
            res%=num;//将每次乘3之后的结果提前给求余，这样就避免了把所有数乘起来后的大数再进行求余的问题
            n-=3;
        }
        //如果n<=4，也就是长度减剩了4以及以下
        //n==4，就乘以2*2，也就是4
        //n==3，就乘以3
        //n==2,就乘以1*2=2
        //n!=1，因为，如果n==1,那么n=1之前的n=4是可以进入循环的，实际上不可以
        //综上，应该最后乘上n
        return (int)(res*n%num);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().cuttingRope(10));
    }
}

