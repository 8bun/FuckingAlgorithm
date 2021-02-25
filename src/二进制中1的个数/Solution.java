package 二进制中1的个数;

/**
 * @description:
 * 有一个规律，就是一个整数n的二进制表示与n-1的二进制表示的
 * 区别是前者与后者相与，得到的结果会消除n中最低位的1，所以，再将n和n-1相与，这
 * 继续会产生此效果，直到得到结果为0
 * @url:https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
 * @author:Jack
 * @createTime:2020/2/23 18:02
 * @level:简单
 */
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int cnt=0;
        while (n!=0){
            n&=n-1;
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().hammingWeight(16));
    }
}
