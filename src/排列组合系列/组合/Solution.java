package 排列组合系列.组合;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * @author cwq
 * @since 2021/03/26
 * @link
 */

public class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        helper(n, k, 1, new ArrayList<>());
        return res;
    }

    private void helper(int n, int k, int level, ArrayList<Integer> curList) {
        if(curList.size() == k) {
            res.add(new ArrayList<>(curList));
            return;
        }
        for (int i = level; i <= n; i++) {
            curList.add(i);
            helper(n, k, i + 1, curList);
            curList.remove(curList.size() - 1);
        }
    }
}
