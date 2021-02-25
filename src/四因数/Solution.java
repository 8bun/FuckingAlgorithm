package 四因数;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/20 11:34
 * @Description:
 * @Url: https://leetcode-cn.com/problems/four-divisors/
 * @限制: 1 <= nums[i] <= 10^5
 * @Level:
 */
public class Solution {
    public int sumFourDivisors(int[] nums) {
        int res=0;
        for (int num:nums){
            res+=sumFourDivisors(num);
        }
        return res;
    }
    //求四因子数的因子和，不是四因子数的返回0
    private int sumFourDivisors(int num){ //获取除1和num之外的最小因子

        if (num<=3)
            return 0;
        int min=1;
        for (int i=2;i<=Math.sqrt(num);i++){
            if (num%i==0){
                min=i;break;
            }
        }
        //要注意num可能本身就是质数
        if (min==1)
            return 0;
        //除了1和本身之外
        //判断最大的因子是不是质数，例如1,3,7,21，判断7是否是质数即可，1,2,3,4,6,12的6就不是所以12不是四因子数
        //还有一种特殊情况,1,3,9，算出的最大因子和最小因子相等，就一定不是
        int max=num/min;
        if (max==min)
            return 0;
        if (max==min*min)  //直接判断是不是最小因子的平方，因为可能1,2,4,8;1,3,9,27 8、27都是四因子
            return 1+min+max+num;
        //判断max是不是质数
        for (int i=2;i<=Math.sqrt(max);i++){
            if (max%i==0)
                return 0;
        }
        return 1+min+max+num;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums={1,2,3,4,5,6,7,8,9,10};
        int res=new Solution().sumFourDivisors(nums);
        System.out.println(res);
    }
}
