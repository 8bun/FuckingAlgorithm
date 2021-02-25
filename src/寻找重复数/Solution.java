package 寻找重复数;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/21 22:16
 * @Description:
 * 因为题目要找的是重复的数，而不是位置，而且数字都在[1,n]范围，所以可以使用中位数来找
 * 以 [3, 2, 2, 5, 4, 1, 6, 7] 为例，一共 8 个数，n + 1 = 8，n = 7，根据题目意思，每个数都在 1 和 7 之间。
 * 例如：区间 [1, 7] 的中位数是 4，【遍历整个数组】，统计小于等于 4 的整数的个数，至多应该为 4 个。换句话说，整个数组里小于等于 4 的
 * 整数的个数如果严格大于 4 个，就说明重复的数存在于区间 [1, 4]，它的反面是：重复的数存在于区间 [5, 7]。
 * 于是，二分法的思路是先猜一个数（有效范围 [left, right]里的中间数 mid），然后统计原始数组中小于等于这个中间数的元素的
 * 个数 cnt，如果 cnt 严格大于 mid，（注意我加了着重号的部分“小于等于”、“严格大于”）依然根据抽屉原理，重复元素就应该在区
 * 间 [left, mid] 里。
 * @Url: https://leetcode-cn.com/problems/find-the-duplicate-number/
 * @限制:
 * 数字都在[1,n]之间，数组长度为n+1
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 * @Level:
 */
public class Solution {

    public int findDuplicate(int[] nums) {
        return findDuplicate(nums,1,nums.length);
    }
    public int findDuplicate(int[] nums,int min,int max) {
        // 在 Java 里可以这么用，当 min + max 溢出的时候，无符号右移保证结果依然正确
        int mid=(min+max)>>>1; //数组的中位数(并不一定在中间位置)
        if(min==max)
            return min;
        if (countMinToMid(mid,nums) > mid)  //如果比中位数小（可以等于）的数的个数都大于了中位数，那么重复元素在[left,mid]的范围
            //例如[3, 2, 2, 5, 4, 1, 6, 7]，中位数为4,那么小于等于它的元素个数为5个，所以重复元素在[1,4]之间
            return findDuplicate(nums,min,mid);
        else return findDuplicate(nums,min+1,max);
    }

    /**
     * 找到数组中比中位数小的数的个数
     * @param mid
     * @param nums
     * @return
     */
    public int countMinToMid(int mid, int[] nums){
        int cnt=0;
        for (int num:nums)
            if (num<=mid)
                cnt++;
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
