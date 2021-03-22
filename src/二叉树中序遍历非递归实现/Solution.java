package 二叉树中序遍历非递归实现;

import javax.swing.tree.TreeNode;
import java.util.Stack;

/**
 * @author cwq
 * @since 2021/03/21
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
    public void inorderTraversal(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        //只要当前节点不为空(即当前节点的左右子树没有访问完毕)或者栈中还有节点(还有节点没有访问)
        while(root != null || !stack.isEmpty()){
            while(root != null){
                //根节点入栈
                stack.push(root);
                //访问左子树
                root = root.left;
            }
            //取出左子树的根节点
            root = stack.pop();
            //打印根节点
            System.out.println(root.val);
            //访问右子树
            root = root.right;
        }
    }
}
