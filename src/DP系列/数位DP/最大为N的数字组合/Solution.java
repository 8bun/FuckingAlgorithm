package DP系列.数位DP.最大为N的数字组合;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/22 20:53
 * @Description:
 * 这里使用规律求解
 * 思路1: 如果N=52525,N的位数为k，D的长度为|D|
 * 对于符合要求的数字num,
 * ①如果num的位数小于N的位数，由于num是由D中的数字组成，
 * 那么num的每一位可以有|D|种选择，所以Ans+=sum(|D|^i),(1<=i<=k-1)
 * ②如果num的位数等于N的位数，由于num是由D中的数字组成，如果num的最高位
 *   a. 小于N的最高位，每一个高位的后4位就可填|D|^4种数字，设p为D中小于N最高位的数的个数，所以Ans+=p*|D|^(k-1)
 *   b. 等于N的最高位，也就是以5开头的num,5 _ _ _ _, 那么就可以利用a的思想，次高位也分小于和等于N的次高位的情况
 *   直到N的位走完。
 *
 * @Url: https://leetcode-cn.com/problems/numbers-at-most-n-given-digit-set/
 * https://www.bilibili.com/video/BV1JW411r7fU?from=search&seid=3685953714158469752
 * @限制:
 * @Level:
 */
public class Solution {

    public int atMostNGivenDigitSet(String[] D, int N) {
        char[] s = String.valueOf(N).toCharArray();
        int n = s.length;
        int ans = 0;
        //num为[1,n-1]位,每个位有D.length种
        for (int i = 1; i < n; i++)
            ans += (int) Math.pow(D.length, i);
        //num为n位
        for (int i = 0; i < n; i++) {//从N的第i位(max)开始找num
            boolean flag = false; //在最后可能要加上1
            for (String d : D) {
                if (d.charAt(0) < s[i]) //在D中找到小于max的数，后面n-i-1位各自有D.length种
                    ans += (int) Math.pow(D.length, n - i - 1);
                else if (d.charAt(0) == s[i]) { //在D中找到了等于max的数，
                    flag = true;
                    break;
                }
            }
            //如果一遍下来，最高位都没有在D中找到等于max的数,case 1
            if (!flag) return ans;
        }
        return ans + 1; //case 2
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] D = {"3","4","8"};
        int N = 4;
        int res = new Solution().atMostNGivenDigitSet(D, N);
        System.out.println(res);
    }
}
