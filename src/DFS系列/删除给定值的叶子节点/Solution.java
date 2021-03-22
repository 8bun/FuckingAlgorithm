package DFS系列.删除给定值的叶子节点;

import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/5/25 23:09
 * @Description:
 * @link https://leetcode-cn.com/problems/delete-leaves-with-a-given-value/
 * @限制:
 * @Level:
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) { val = x; }
}

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }

    /**
     * 返回删除左右子树的target结点后返回的树根
     *
     * @param root
     * @param target
     *
     * @return
     */
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) return null;

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        if (root.val == target && root.left == null && root.right == null) return null;
        return root;
    }
}
