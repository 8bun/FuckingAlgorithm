package 数字序列中某一位的数字;

/**
 * @description：
 * 0位：数字0，一共1位
 * 1~9位：数字1~9, 一共9位
 * 10~189位：数字10~99, 一共9×10×2位
 * 190~2889位：数字100~999, 一共9×100×3=9*10^(3-1)*3位
 * 求出某一位所在的区间，再根据之前区间的总位数，求出在该区间的位，然后
 * 根据该区间的数字位数（相同）求出该位落在哪个数字的哪一位
 *
 * @url：https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/
 * @限制：0 <= n < 2^31
 * @author：Jack
 * @createTime：2020/3/3 14:08
 * @level：中等
 */
public class Solution {

    public int findNthDigit(int n) {

        return findScope(n);
    }

    /**
     * 因第0位为0，所以可忽略不计
     * 寻找位数区间
     * @param n
     * @return
     */
    private int findScope(int n){
        if(n<0){
            return 0;
        }
        else if(n/10==0)
            return n;
        int digit=1; //某个单位的区间
        long base=9; //每个区间的总位数，使用long存储，因为后面有涉及到10的次方运算，防止溢出
        int sub_digits=digit*(int)base;
        int tmp=n;
        tmp-=sub_digits;
        while (tmp>0){
            digit+=1;
            base=9*(long)Math.pow(10,digit-1)*digit;  //该单位区间总共的位数
            if(base>Integer.MAX_VALUE) {
                break;
            }
            tmp-=base;
            if(tmp>0)
                sub_digits+=base;
        }
//            System.out.println("在区间的单位为："+digit);
//            System.out.println("前面所有区间的总位数为: "+sub_digits);
        int numOfdigit=n-sub_digits;  //在单位区间内的第几位
        int num;  //所在单位区间的哪个数字
        int first=(int)Math.pow(10,digit-1);//单位区间的第一个数字
        int r=numOfdigit%digit;
        int s=numOfdigit/digit;
        if(r!=0) {
            num=first+s;
//                System.out.println("在区间的第:"+numOfdigit+"位，所在数字为:"+num);
            return String.valueOf(num).charAt(r-1)-'0';
        }else {
            num=first+s-1;
//                System.out.println("在区间的第:"+numOfdigit+"位，所在数字为:"+num);
            return String.valueOf(num).charAt(String.valueOf(num).length()-1)-'0';
        }
    }
    public static void main(String[] args) {
        new Solution().findNthDigit(2147483647);
    }
}
