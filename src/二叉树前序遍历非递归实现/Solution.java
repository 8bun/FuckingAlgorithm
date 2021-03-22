package 二叉树前序遍历非递归实现;

import java.util.ArrayList;
import java.util.List;
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
    public void preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if(root == null)//树为空
            return;
        stack.add(root);//将根节点压入栈中
        while(!stack.isEmpty()){//只要栈不为空，执行循环
            TreeNode node = stack.pop();//取出栈顶元素打印，此时的栈顶元素是以node为根的子树的根
            System.out.println(node.val);
            if(node.right != null)//将右子树压入栈中
                stack.push(node.right);
            if(node.left != null)//将左子树压入栈中
                stack.push(node.left);
        }
    }
}
