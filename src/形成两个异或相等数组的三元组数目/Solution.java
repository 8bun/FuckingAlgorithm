package 形成两个异或相等数组的三元组数目;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/10 11:13
 * @Description:
 * @Url: https://leetcode-cn.com/contest/weekly-contest-188/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/
 * @限制:
 * @Level:
 */

public class Solution {
    public int countTriplets(int[] arr) {
        int res=0;
        int len=arr.length;
        for (int i=0;i<len-1;i++){
            for (int j=i+1;j<len;j++){
                for (int k=j;k<len;k++){
                    int a_l=i,a_r=j-1,b_l=j,b_r=k;
                    int a=0,b=0;
                    for (int m=a_l;m<=a_r;m++)
                        a^=arr[m];
                    for (int n=b_l;n<=b_r;n++)
                        b^=arr[n];
                    if (a==b)res++;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr={2,3,1,6,7};
        int res=new Solution().countTriplets(arr);
        System.out.println(res);
    }
}
