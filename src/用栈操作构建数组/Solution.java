package 用栈操作构建数组;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/5/10 10:34
 * @Description:
 * @link https://leetcode-cn.com/contest/weekly-contest-188/problems/build-an-array-with-stack-operations/
 * @限制:
 * @Level:
 */
public class Solution {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }

    public List<String> buildArray(int[] target, int n) {
        int num = 0, cur = 1;
        List<String> res = new ArrayList<>();
        for (; num < target.length - 1; num++) {
            if (num == 0) {
                while (cur < target[num]) {
                    res.add("Push");
                    res.add("Pop");
                    cur++;
                }
            }
            res.add("Push");
            int sub = target[num + 1] - target[num];
            while (sub != 1) {
                res.add("Push");
                res.add("Pop");
                sub--;
            }
        }
        res.add("Push");
        return res;
    }
}
