package 寻找重复的子树;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cwq
 * @since 2021/03/19
 */

public class Solution {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) { this.val = val; }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    private HashMap<String, Integer> memo = new HashMap<>();
    LinkedList<TreeNode> res = new LinkedList<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    private String traverse(TreeNode root) {
        if(root == null) {
            return "#";
        }
        String subTree = traverse(root.left) + "," + traverse(root.right) + "," + root.val;
        int freq = memo.getOrDefault(subTree, 0);
        if(freq == 1) {
            res.add(root);
        }
        memo.put(subTree, freq + 1);
        return subTree;
    }
}
