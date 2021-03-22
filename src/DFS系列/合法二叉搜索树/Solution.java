package DFS系列.合法二叉搜索树;

import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/5/1 22:40
 * @Description: 实现一个函数，检查一棵二叉树是否为二叉搜索树。
 * @link https://leetcode-cn.com/problems/legal-binary-search-tree-lcci/
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

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        TreeNode maxLeft = root.left, minRight = root.right;
        // 找寻左子树中的最右（数值最大）节点
        while (maxLeft != null && maxLeft.right != null)
            maxLeft = maxLeft.right;
        // 找寻右子树中的最左（数值最小）节点
        while (minRight != null && minRight.left != null)
            minRight = minRight.left;

        // 当前层是否合法
        boolean ret = (maxLeft == null || maxLeft.val < root.val) && (minRight == null || root.val < minRight.val);
        return isValidBST(root.left) && isValidBST(root.right) && ret;
    }
}
