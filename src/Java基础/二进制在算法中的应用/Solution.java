package Java基础.二进制在算法中的应用;


import org.junit.Test;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime： 2020/4/10 14:39
 * @level：
 */
public class Solution {

    private int num = 32;

    private int a = 2;
    private int b = 5;

    /**
     * 计算数字的二进制位的1的个数
     */
    @Test
    public void cal_One() {

        int sum = 0;
        while (num != 0) {
            num = num & (num - 1);
            sum++;
        }
        System.out.println(sum);
    }

    /**
     * 判断一个数是否是2^n
     * 如果是，那么它的二进制位只有一位
     *
     * @param
     */
    @Test
    public void is_pow() {
//        int sum=0;
////        while (num!=0){
////            num=num&(num-1);
////            sum++;
////        }
////
////        if(sum==1)
////            System.out.println("Yes");
////        else System.out.println("No");
        //或者
        num = num & (num - 1);
        if (num == 0)
            System.out.println("Yes");
        else System.out.println("No");
    }

    /**
     * 判断一个数是否是4的幂
     * 假设num=4^n
     * 那么一定是num=2^(2n)
     * 所以先判断它是不是2的幂次，
     * 如果是的话，那么根据数是4的幂的性质：
     * 如果一个数a是4的幂，那么这个数可以表示为某个数b*4
     * 也就是b的二进制表示左移两位得到a，并且a=4*4*...4；
     * 那么a就可以看成4的二进制表示100（第三位为1），往左移若干个两位
     * 也就是数是4的幂的话，那么它的1（只有一个）一定位于该数的二进制的某个奇数位上
     * 假设操作系统表示一个数最多是32位，
     * 那么有两个特殊的数：
     * 0xaaaaaaaa=1010...1010=m (偶数位都是1)
     * 0x55555555=10101...0101=k（奇数位都是1，溢出表示）
     * 那么利用a和数k进行相与，如果该数的二进制的某个奇数位存在1的话，那么结果一定不等于0，它一定是4的幂次
     */
    @Test
    public void is_4pow() {
        int n = 2;
        int tmp = n & (n - 1);
        if (tmp == 0) {
            int tmp2 = 0x55555555 & n;
            if (tmp2 != 0)
                System.out.println("Yes");
            else System.out.println("No");
        } else System.out.println("No");
    }

    /**
     * 判断是不是3的幂
     * 3的幂的特点：如果一个整数N是3的幂，那么其所有约数都是3的幂。那么，换一个角度，如果n小于N且是N的约数，那么其一定是3的幂；
     */
    @Test
    public void is_3pow() {
        int n = 27;
        int max = (int) Math.pow(3, (int) (Math.log(0x7fffffff) / Math.log(3)));
        if (max % n == 0)
            System.out.println("Yes");
        else System.out.println("No");
    }

    /**
     * 第一个位为1的数，一定是奇数
     */
    @Test
    public void isOddNum() {
        int num = 89;
        if ((num & 1) == 0) {
            System.out.println("偶数");
        } else System.out.println("奇数");
    }

    /**
     * 利用异或的自反性
     * 不使用算术运算符，求两数的和
     * 例如交换两个整数a=10100001，b=00000110的值，可通过下列语句实现：
     * 　a = a^b； 　　//a=10100111
     * 　b = b^a； 　　//b=10100001
     * 　a = a^b； 　　//a=00000110
     */
    @Test
    public void swap() {
        System.out.print("a=" + a + ",");
        System.out.println("b=" + b);
        a = a ^ b;
        b = b ^ a;
        a = a ^ b;
        System.out.print("a=" + a + ",");
        System.out.println("b=" + b);
    }

    /**
     * 使某些特定的位翻转
     * 例如对数10100001的第2位和第3位翻转，则可以将该数与00000110进行按位异或运算。
     * 10100001^00000110 = 10100111
     */
    @Test
    public void change() {
        int place1 = 2;
        int place2 = 3;
        int num = 18;
        int tmp_num = (int) Math.pow(2, place1 - 1) + (int) Math.pow(2, place2 - 1);
        System.out.println(num & tmp_num);
    }

    /**
     * 判断两个数是否相同
     * 应用：找出数组中唯一一个不重复的数字
     */
    @Test
    public void isSame() {
        int n = a ^ b;
        if (n == 0)
            System.out.println("Same");
        else System.out.println("Different");
    }

    /**
     * >>表示右移，如果该数为正，则高位补0，若为负数，则高位补1
     * >>>表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0
     * 利用左移右移进行幂运算
     * a<<n=a* 2^n，
     * a>>n=a/(2^n)
     */
    @Test
    public void pow() {
        System.out.println(2 << 4);
        System.out.println(16 >> 2);
    }

    /**
     * 不使用加号的加法:
     * 假设
     *  1011
     * +1101，对应位都是1的会产生进位1，（想到了&的性质）进位给下一位计算
     * 那么我们求&一下：
     *  1011
     * &1101
     * =1001
     * 然后该数左移1位，（进位要给下一位计算）
     * 得到10010的一轮进位
     *  1011
     * +1101，不加上进位的情况，得到0110，（想到^的性质，只有1和0才能产生1）
     * 然后加上进位：
     *  0110
     * +10010，问题转化为求0110(a)和进位（b）的和，继续循环，直到进位为0，a就是结果
     */
    @Test
    public void add() {
        int a = 20;
        int b = 89;
        while (b != 0) {
            int tmp = a;
            a = a ^ b; //不加进位的相加
            b = (tmp & b) << 1; //进位,已经移到相应被加的位置
        }
        System.out.println(a);
    }

    /**
     * 符号变换
     * 如对于-11和11，可以通过下面的变换方法将-11变成11
     * 1111 0101(二进制) –取反-> 0000 1010(二进制) –加1-> 0000 1011(二进制)
     * 同样可以这样的将11变成-11
     * 0000 1011(二进制) –取反-> 0000 0100(二进制) –加1-> 1111 0101(二进制)
     */
    @Test
    public void SignReversal() {
        int num = - 12;
        System.out.println(~ num + 1);
    }

    /**
     * 求绝对值：
     * 移位来取符号位判断正负
     */
    @Test
    public void abs() {
        int a = 12;
        int i = a >> 31;
        int res = i == 0 ? a : (~ a + 1);
        System.out.println(res);
    }

    /**
     * HashMap寻找一个<=容量的最大值，该最大值为2的幂次
     * 采用多轮的 右移再和本身进行或运算
     * 每一轮保证了数的二进制的低位均是1
     * 最后将最后一轮得到的数与(最后一轮得到的值>>>1)进行相减，即可
     * 得到一个二进制只有一个1的数了
     */
    @Test
    public void hashMapTest() {
        int i = 9;  //0000 1010 ，该值二进制为4位，所以后面得到了0000 1111之后就不会变了
        i |= (i >> 1);  //0000 1111
        i |= (i >> 2);  //0000 1111
        i |= (i >> 4);  //0000 1111
        i |= (i >> 8);  //0000 1111
        i |= (i >> 16); //0000 1111
        /**
         *  0000 1111
         * -0000 0111
         * =0000 1000
         * 为什么移动16后面就不移动了？因为1+2+4+8+16=31
         */
        int res = i - (i >>> 1);
        System.out.println(res);

        //然后再利用该函数找到一个大于它的最小的2的次幂数
        int j = 14;
        /**
         * 1101 << 1 = 0001 1010 一定大于或等于16，并且小于32(最大0001 1111 < 32)，所以再找出
         * <=该数的最大值，该最大值为2的幂次，就是大于14的最小的2的次幂数
         */
        j = (j - 1) << 1;
        j |= (j >> 1);
        j |= (j >> 2);
        j |= (j >> 4);
        j |= (j >> 8);
        j |= (j >> 16);
        int res1 = j - (j >>> 1);
        System.out.println(res1);
    }

}
