package 回溯递归系列.不同的二叉搜索树II;

import java.util.ArrayList;
import java.util.List;

/**
 * https://grandyang.com/leetcode/95/
 * 这道题是之前的 Unique Binary Search Trees 的延伸，之前那个只要求算出所有不同的二叉搜索树的个数，这道题让把那些二叉树都建立出来。
 * 这种建树问题一般来说都是用递归来解，这道题也不例外，划分左右子树，递归构造。这个其实是用到了大名鼎鼎的分治法 Divide and Conquer，
 * 似的题目还有之前的那道 Different Ways to Add Parentheses 用的方法一样，用递归来解，划分左右两个子数组，递归构造。
 * 刚开始时，将区间 [1, n] 当作一个整体，然后需要将其中的每个数字都当作根结点，其划分开了左右两个子区间，然后分别调用递归函数，会得到两个结点数组，
 * 接下来要做的就是从这两个数组中每次各取一个结点，当作当前根结点的左右子结点，然后将根结点加入结果 res 数组中即可
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

    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }

    /**
     * 返回[start, end]所有可能的树根
     * 子树也是一个树，跟左右无关
     *
     * @param start
     * @param end
     * @return
     */
    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            // 没有子树了，叶节点仍然是null
            res.add(null);
            return res;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftNodes = helper(start, i - 1);
            List<TreeNode> rightNodes = helper(i + 1, end);

            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
