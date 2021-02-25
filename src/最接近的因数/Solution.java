package 最接近的因数;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/23 19:02
 * @Description:
 * @Url: https://leetcode-cn.com/contest/weekly-contest-177/problems/closest-divisors/
 * @限制:
 * @Level:
 */
public class Solution {

    public int[] closestDivisors(int num) {
        int[] res = new int[2];
        int tmp = (int) Math.sqrt(num + 1);
        if (tmp * tmp == num + 1){
            res[0] = tmp;
            res[1] = tmp;
            return res;
        }
        tmp = (int) Math.sqrt(num + 2);
        if (tmp * tmp == num + 2){
            res[0] = tmp;
            res[1] = tmp;
            return res;
        }

        int min1 = f(num + 1);
        int sub1 = (num + 1) / min1 - min1;

        int min2 = f(num + 2);
        int sub2 = (num + 2) / min2 - min2;

        if (sub1 < sub2){
            res[0] = min1;
            res[1] = (num + 1) / min1;
        }else {
            res[0] = min2;
            res[1] = (num + 2) / min2;
        }
        return res;
    }
    private int f(int num){
        for (int i = (int) Math.sqrt(num); i >= 2; i--){
            if (num % i == 0)
                return i;
        }
        return 1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int res[] = new Solution().closestDivisors(123);
    }
}
