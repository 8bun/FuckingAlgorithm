package Java基础.快速幂;

/**
 * @description： 取模运算法则：
 * (a + b) % p = (a % p + b % p) % p （1）
 * (a - b) % p = (a % p - b % p ) % p（2）
 * (a * b) % p = (a % p * b % p) % p （3）
 * (a*b*c)%d=(a%d*b%d*c%d)%d;
 * 那么根据上面，求(A^B)%1000的值就可以利用这个法则来求
 * 比如求(2^89)%1000
 * 那么就可以(2*2*2*...)%1000
 * 每次对结果进行取模。未完待续
 * @url： https://blog.csdn.net/qq_19782019/article/details/85621386
 * @限制：
 * @author：Jack
 * @createTime: 2020/4/7 23:23
 * @level：
 */
public class normalPower {

    public static void main(String[] args) {}

    //虽然这个求幂的方法很有用，并且提交给OJ也直接Accept了，但是我们来考虑一下这个算法的时间复杂度，
    // 假设我们求2的100次方，那么将会执行100次循环。如果我们分析一下这个算法，就会发现这个算法的时间复杂度为O(N),
    // 其中N为指数。
    // 求一下小的结果还好，那如果我们要求2的1000000000次方呢
    long normalPower(long base, long power) {
        long result = 1;
        for (int i = 1; i <= power; i++) {
            result = result * base;
            result = result % 1000;
        }
        return result % 1000;
    }

    //快速幂做法
    long fastPower(long base, long power) {
        long result = 1;
        while (power > 0) {
            if (power % 2 == 1) {
                result = result * base % 1000;
            }
            power = power / 2;
            base = (base * base) % 1000;
        }
        return result;
    }
}
