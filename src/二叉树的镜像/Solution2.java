package 二叉树的镜像;

import java.util.Stack;

/**
 * @description:使用栈或队列解决，将其首部节点出栈或队列，然后交换其左右子树，再将左节点和右节点入栈或队列，进入下一次循环
 * 直到栈或队列为空
 * @url:
 * @author:Jack
 * @createTime:2020/2/27 10:13
 * @level:
 */

public class Solution2 {
    public TreeNode mirrorTree(TreeNode root) {
        Stack<TreeNode> s=new Stack<>();
        s.push(root);
        while (!s.isEmpty()){
            TreeNode node=s.peek();
            s.pop();
            if(node==null)
                continue;
            TreeNode tmp=node.left;
            node.left=node.right;
            node.right=tmp;
            s.push(node.left);
            s.push(node.right);
        }
        return root;
    }
}
