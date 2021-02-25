package 最小的k个数;

import java.util.Arrays;

/**
 * @description：
 * @url：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 * @限制：0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 * @author: Jack
 * @createTime：2020/3/2 9:47
 * @level：
 */
public class Solution {
    //解1：
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int [] a=new int[k];
        for(int i=0;i<k;i++){
            a[i]=arr[i];
        }
        return a;
    }
}
