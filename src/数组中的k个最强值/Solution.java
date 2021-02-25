package 数组中的k个最强值;

import java.util.Arrays;
import java.util.Comparator;
/**
 * @Author: Jack
 * @Date: 2020/6/8 22:55
 * @Description: 
 * @Url:  https://leetcode-cn.com/contest/weekly-contest-192/problems/the-k-strongest-values-in-an-array/
 * @限制: 
 * @Level: 
 */
public class Solution {
    public int[] getStrongest(int[] arr, int k) {
        int len = arr.length;
        Arrays.sort(arr);
        Integer[] a = new Integer[len];
        for (int i = 0; i < len; i++) {
            a[i] = arr[i];
        }
        int mid = arr[(len - 1) / 2];
        Arrays.sort(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int a = Math.abs(o1 - mid);
                int b = Math.abs(o2 - mid);
                if(a != b) {
                    return Integer.compare(b, a);
                }
                return Integer.compare(o2, o1);
            }
        });
        int[] res = new int[k];
        for (int i = 0; i < k; i++)
            res[i] = a[i];
        return res;
    }

    public static void main(String[] args) {
        int arr[] = {6,7,11,7,6,8};
        int k = 5;
        int[] res = new Solution().getStrongest(arr, k);
        for (int i : res)
            System.out.print(i + " ");
        System.out.println();
    }
}
