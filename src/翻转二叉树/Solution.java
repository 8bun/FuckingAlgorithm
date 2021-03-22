package 翻转二叉树;

import javax.swing.tree.TreeNode;

/**
 *
 * @author cwq
 * @since 2021/03/19
 * @link https://leetcode-cn.com/problems/invert-binary-tree/
 */

public class Solution {

     class TreeNode {
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
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        else {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }
}
