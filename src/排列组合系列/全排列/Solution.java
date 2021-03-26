package 排列组合系列.全排列;

import java.util.LinkedList;
import java.util.List;

/**
 *  输入一组不重复的数字，返回它们的全排列
 * @link https://leetcode-cn.com/problems/permutations/
 * @author cwq
 */
public class Solution {
    private List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> part = new LinkedList<>();
        backtrace(nums, part);
        return res;
    }

    private void backtrace(int[] num, LinkedList<Integer> part) {
        if (part.size() == num.length) {
            res.add(new LinkedList<>(part));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            if (part.contains(num[i]))
                continue;
            part.add(num[i]);//作出选择
            backtrace(num, part); //进入决策
            part.removeLast(); //撤销选择
        }
    }
}
