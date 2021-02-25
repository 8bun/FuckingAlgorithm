package DFS系列.二叉树中的最大路径和;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/13 17:40
 * @Description:
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径定义为"从任意的起始节点"开始沿着它连接的父或子结点到树中"任何节点"的节点序列。(走过的路不可以回去)
 * 该路径"至少包含一个"节点，且不一定经过根节点。
 * 这里采用dfs的方式找到1个节点到另外一个节点的最大路径和
 * 对每一个根节点来说，最大路径和的求法如下：
 * 首先把树划分为左右子树，以左子树为例，每次只能选左子树的左右子树中的一条路径来走（否则就不是路径了），因此在递归函数中每次返回左右子树的较大值来递加；
 * 递归完左右子树之后，max_sum记录左子树路径加和、右子树路径加和（左右子树事先与0比较，为负则事先归零相当于不再走这边）、以及根节点自身的值，作为最终返回值。
 * @Url: https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
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
    private int res;
    public int maxPathSum(TreeNode root) {
        if (root == null)return 0;
        res = root.val;
        dfs(root);
        return res;
    }
    private int dfs(TreeNode root){
        if (root == null)return 0;
        //单边最大和，如果子树路径和为负则应当置0表示最大路径不包含子树
        int left_max = Math.max(dfs(root.left), 0);
        int right_max = Math.max(dfs(root.right), 0);

        //以当前节点为根节点,判断在该节点包含左右子树的路径和是否大于当前最大路径和
        //这句可以看作是当左右都为负，只取中间一点的值
        res = Math.max(res, left_max + right_max + root.val);
        //返回以root结点为根结点的单边树最大和
        return root.val + Math.max(left_max, right_max);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
