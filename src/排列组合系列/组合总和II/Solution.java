package 排列组合系列.组合总和II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cwq
 * @since 2021/03/26
 * @link
 */

public class Solution {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
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
            // 防止res出现重复
            /**
             * https://leetcode-cn.com/problems/combination-sum-ii/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-3/225211/
             * candidates = [10,1,2,7,6,1,5], target = 8
             * 有重复元素的话，比如测试用例中的第一个1和第二个1，都会有1,7组成8，
             * 这样就产生了重复的list。因为都是从当前数开始遍历，
             * 所以加这一层的意思就是过滤掉重复的数，但是第一个1依然能使用第二个1，而第二个1是失去了作用的。
             */
            if(i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            curList.add(candidates[i]);
            // 防止重复使用某位置
            combinationSumDFS(candidates, target - candidates[i], i + 1, curList);
            curList.remove(curList.size() - 1);
        }
    }
}
