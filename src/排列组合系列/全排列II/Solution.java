package 排列组合系列.全排列II;

import java.util.*;

/**
 * 给定一个可包含重复数字的序列nums，按任意顺序 返回所有不重复的全排列。
 * @author cwq
 * @since 2021/03/26
 * @link https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/
 */

public class Solution {
    private boolean[] vis;
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {

        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrace(nums, 0, new ArrayDeque<>());
        return res;
    }

    private void backtrace(int[] nums, int idx, Deque<Integer> curList) {
        if (idx >= nums.length) {
            res.add(new ArrayList<>(curList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(vis[i] || (i > 0 && nums[i - 1] == nums[i] && !vis[i - 1])) {
                continue;
            }
            vis[i] = true;
            curList.addLast(nums[i]);
            backtrace(nums, idx + 1, curList);
            curList.removeLast();
            vis[i] = false;
        }
    }
}
