package 制作m束花所需的最少天数;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/6/14 11:53
 * @Description:
 * 典型的二分，我们可以知道如果mid天能够得到m组k束花，那么比mid大的所有天数都是可以的，我们现在就要看能否在更少的天数获得m*k朵花。
 * 至于判断mid天能否获得m*k朵花直接遍历就行了
 * @Url:  https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/
 * @限制: 
 * @Level: 
 */
public class Solution {

    public int minDays(int[] b, int m, int k) {
        int len = b.length;
        if(len < m * k) return -1;
        int[] s = Arrays.copyOf(b, len);
        Arrays.sort(s);
        return binarySearch(0, len - 1, s, b, m, k);
    }
    private int binarySearch(int l, int r, int[] s, int[] b, int m, int k) {
        if(l >= r) return s[l];
        int mid = (l + r) >> 1;
        boolean mid_res = check(b, s[mid], m, k);
        if (mid_res) return binarySearch(l, mid, s, b, m, k);
        else return binarySearch(mid + 1, r, s, b, m, k);
    }
    private boolean check(int[] b, int num, int m, int k) {
        int cnt = 0, help = 0;
        for(int i : b) {
            if(i <= num) {
                if (++help >= k){
                    cnt++;
                    help = 0;
                }
            }
            else help = 0; //重新统计连续
            if (cnt == m) break;
        }
        return cnt == m;
    }

    public static void main(String[] args){
         Scanner scanner=new Scanner(System.in);
         int[] b = {7,7,7,7,12,7,7};
         int res = new Solution().minDays(b, 2, 3);
         System.out.println(res);
    }
}
