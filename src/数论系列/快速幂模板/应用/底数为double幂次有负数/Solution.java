package 数论系列.快速幂模板.应用.底数为double幂次有负数;import java.util.Scanner;
/**
 * @Author: Jack
 * @Date: 2020/6/11 11:47
 * @Description: 
 * @Url: https://leetcode-cn.com/problems/powx-n/
 * @限制:
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 * @Level: 
 */
public class Solution {

    public double myPow(double x, int n) {
        if (x == 0.0) return x;
        long b = n;
        if(n < 0) { // 幂次为负->底数变倒数，幂次变正
            x = 1.0 / x;
            //Java 代码中 int32 变量 n∈[−2147483648,2147483647] ，因此当 n = -2147483648 时执行 n =−n 会因越界而赋值出错。解决方法是先将 n 存入 long 变量 b ，后面用 b 操作即可
            b = -b;
        }
        return fastPow(x, b);
    }
    private double fastPow(double a, long k) {
        double res = 1.0;
        while (k != 0) {
            if ((k & 1) == 1) //如果k的二进制最后一位是1的话（k是奇数）就乘以一份a，化成a * a^(k-1),k-1此时为偶数
                res = res * a;
            /**
             * 例如3^23 = 3^1 * 3^2 * 3^4 * 3^16...，这里是根据幂的二进制进行分解的
             * 23=(10111)=1+2+4+16
             * a和k的演化是
             * a: 3       3^2       3^4       3^8                                    3^16  3^32
             * k: 10111   1011      101       10(此时不会进入前面的条件，a不会乘以res)      1     0
             */
            a = a * a;
            k >>= 1;  //消去最后一位二进制
        }
        return res;
    }

    public static void main(String[] args){
         Scanner scanner=new Scanner(System.in);
         double res = new Solution().myPow(2.00, Integer.MIN_VALUE);
         System.out.println(res);
    }
}
