package 二叉搜索树的第k大节点;

/**
 * @description： 给定一棵二叉搜索树，请找出其中第k大的节点。递归模拟右子树-根-左子树的遍历
 * @url： https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 * @限制： 1 ≤ k ≤ 二叉搜索树元素个数
 * @author：Jack
 * @createTime：2020/3/6 19:16
 * @level：简单
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {

    private int cnt=0;
    private int ans;
    private int k;
    public int kthLargest(TreeNode root, int k) {
        this.k=k;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root){
        if(root==null)
            return ;  //返回到父调用函数
        dfs(root.right);
        if(++cnt==k) {
            ans = root.val;
            return;
        }
        dfs(root.left);
    }
}
