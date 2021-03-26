package 排列组合系列.组合总和;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cwq
 * @since 2021/03/26
 * @link https://www.cnblogs.com/grandyang/p/4419259.html
 */

public class Solution {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combinationSumDFS(candidates, target, 0, new ArrayList<>());
        return res;
    }

    private void combinationSumDFS(int[] candidates, int target, int start, ArrayList<Integer> curList) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(curList));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            curList.add(candidates[i]);
            combinationSumDFS(candidates, target - candidates[i], i, curList);
            curList.remove(curList.size() - 1);
        }
    }
}
