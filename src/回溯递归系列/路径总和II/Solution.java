package 回溯递归系列.路径总和II;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯算法原理讲解：https://labuladong.github.io/algo/1/8/
 * https://leetcode.cn/problems/path-sum-ii/
 */
public class Solution {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        pathSum(root, targetSum, new ArrayList<>());
        return res;
    }

    private void pathSum(TreeNode curRoot, int targetSum, List<Integer> curList) {
        if (curRoot == null) {
            return;
        }
        // 进入子树前，选择
        curList.add(curRoot.val);
        if (curRoot.left == null && curRoot.right == null) {
            if (curRoot.val == targetSum) {
                res.add(new ArrayList<>(curList));
            }
        }
        if (curRoot.left != null) {
            pathSum(curRoot.left, targetSum - curRoot.val, curList);
            // 进入子树后，回来撤销选择
            curList.remove(curList.size() - 1);
        }
        if (curRoot.right != null) {
            pathSum(curRoot.right, targetSum - curRoot.val, curList);
            curList.remove(curList.size() - 1);
        }
    }
}