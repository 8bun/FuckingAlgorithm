package 按既定顺序创建目标数组;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/4/20 11:12
 * @Description:
 * @link https://leetcode-cn.com/contest/weekly-contest-181/problems/create-target-array-in-the-given-order/
 * @限制:
 * @Level:
 */
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }

    public int[] createTargetArray(int[] nums, int[] index) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < index.length; i++)
            list.add(index[i], nums[i]);
        int[] target = new int[list.size()];
        int cur = 0;
        while (! list.isEmpty())
            target[cur++] = list.removeFirst();
        return target;
    }
}
