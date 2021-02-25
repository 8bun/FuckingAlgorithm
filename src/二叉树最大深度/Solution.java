package 二叉树最大深度;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @description：
 * @url： https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/
 * @限制：
 * @author：Jack
 * @createTime： 2020/4/1 9:33
 * @level：
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    /**
     * 递归写法
     * @param root
     * @return
     */
//    public int maxDepth(TreeNode root) {
////        if(root == null) return 0;
////        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
////    }

    /**
     * 非递归
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        int depth=0;
        while (!queue.isEmpty()){
            int size=queue.size();
            depth++;
            // 遍历size个节点的下一层的左右节点（不存在递归，这样就可以遍历所有的左右子树节点），
            // 遍历完进入下一次循环，depth+1;
            while (size-- != 0) {
                TreeNode first=queue.poll();
                if(first.left!=null){
                    queue.offer(first.left);
                }
                if(first.right!=null){
                    queue.offer(first.right);
                }
            }
        }
        return depth;
    }
}
