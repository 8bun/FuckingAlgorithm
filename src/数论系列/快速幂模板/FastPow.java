package 数论系列.快速幂模板;

import java.util.Scanner;
/**
 * @Author: Jack
 * @Date: 2020/6/11 10:59
 * @Description:
 * 快速幂由于过于简单，通常作为某个复杂算法中间一步，比如欧拉公式，欧几里得算法
 * 因为指数在每次运算中都会缩小一半，时间复杂度为O(log2k)。
 * https://leetcode-cn.com/problems/powx-n/solution/50-powx-n-kuai-su-mi-qing-xi-tu-jie-by-jyd/
 * https://leetcode-cn.com/problems/super-pow/solution/you-qian-ru-shen-kuai-su-mi-suan-fa-xiang-jie-by-l/
 * @Url: 
 * @限制: 
 * @Level: 
 */
public class FastPow {

    private static final int MOD = 1_000_000_00_7;
    private long res;

    /**
     * 迭代写法
     * 求a^k
     * @param a
     * @param k
     * @return
     */
    long fastPow(int a, int k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) //如果k的二进制最后一位是1的话（k是奇数）就乘以一份a，化成a * a^(k-1),k-1此时为偶数
                res = res * a % MOD;
            /**
             * 例如3^23 = 3^1 * 3^2 * 3^4 * 3^16...，这里是根据幂的二进制进行分解的
             * 23=(10111)=1+2+4+16
             * a和k的演化是
             * a: 3       3^2       3^4       3^8                                    3^16  3^32
             * k: 10111   1011      101       10(此时不会进入前面的条件，a不会乘以res)      1     0
             */
            a = a * a % MOD;
            k >>= 1;  //消去最后一位二进制
        }
        return res;
    }

    /**
     * 递归写法，需要注意将a的半幂先保存，防止算2次
     * @param a
     * @param k
     * @return
     */
    long fastPow2(int a, int k) {
        if (k == 0) return 1;
        if ((k & 1) != 0) return fastPow2(a, k - 1) * a % MOD;
        else {
            long tmp = fastPow(a, k / 2);
            return tmp * tmp % MOD;
        }
    }
    public static void main(String[] args){
         Scanner scanner=new Scanner(System.in);
         long res = new FastPow().fastPow(3,23);
         long res2 = new FastPow().fastPow2(3,23);
         System.out.println(res);
         System.out.println(res2);
    }
}
