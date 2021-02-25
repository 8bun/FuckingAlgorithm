package 剪绳子;

/**
 * @description:贪心分析见题解
 * https://leetcode-cn.com/problems/jian-sheng-zi-lcof/solution/mian-shi-ti-14-i-jian-sheng-zi-tan-xin-si-xiang-by/
 * @url:https://leetcode-cn.com/problems/jian-sheng-zi-lcof/
 * @author:Jack
 * @createTime:2020/2/23 10:29
 * @level:中等
 */
public class Solution {

    public int cuttingRope(int n) {
        //n=2,1*1,n=3,1*2
        if(n<=3)
            return n-1;
        //尽可能分成多个3的
        int num_3=n/3;
        //剩余不足3的余数，b=0,1,2
        //b==1时，最后的一个3与1合并分成2+2，因为3*1<2*2
        int b=n%3;
        if (b==0)
            return (int)Math.pow(3,num_3);
        if (b==1)
            return (int)Math.pow(3,num_3-1)*2*2;
        return (int)Math.pow(3,num_3)*2;

    }
}
