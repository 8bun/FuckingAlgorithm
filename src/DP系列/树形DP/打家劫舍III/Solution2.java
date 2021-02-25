package DP系列.树形DP.打家劫舍III;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/13 23:57
 * @Description:
 * 我们换一种办法来定义此问题:
 * 每个节点可选择偷或者不偷两种状态，根据题目意思，相连节点不能一起偷
 * - 当前节点选择偷时，那么两个孩子节点就不能选择偷了
 * - 当前节点选择不偷时，两个孩子节点只需要拿最多的钱出来就行(两个孩子节点偷不偷没关系)
 *
 * 我们使用一个大小为 2 的数组来表示 int[] res = new int[2] 0 代表不偷，1 代表偷
 * 任何一个节点能偷到的最大钱的状态可以定义为:
 * 1.当前节点选择不偷：当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
 * 2.当前节点选择偷：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
 *
 * 表示为公式如下
 * root[0] = Math.max(rob(root.left)[0], rob(root.left)[1]) + Math.max(rob(root.right)[0], rob(root.right)[1])
 * root[1] = rob(root.left)[0] + rob(root.right)[0] + root.val;
 * 或者这样看 rob(root)表示考虑偷root为根的树的最大值，如果不偷root，那就是考虑rob(root.left) + rob(root.right)；如果偷root，那就相当于考虑四个孙子
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution2 {
    public int rob(TreeNode root) {

        if (root == null) return 0;
        int[] res = helper(root);    //res[0]表示root不偷的话的最大钱数，res[1]表示偷的话的最大钱数
        return Math.max(res[0], res[1]);
    }

    /**
     * 这里的递归方式类似于 DFS系列.二叉树的直径中的dfs递归
     * @param root
     * @return
     */
    private int[] helper(TreeNode root){

        if (root == null) return new int[2];
        int[] curRes = new int[2]; //记录当前以root偷和不偷下的两种结果
        //获取单边的两种结果（每个孩子也有两种情况）
        int[] left = helper(root.left);
        int[] right =helper(root.right);
        //当前root结点选择不偷，当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱  (取左孩子偷和不偷的最大)
        curRes[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //当前root结点选择偷，当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
        curRes[1] = left[0] + right[0] + root.val;

        //返回当前root结点的两种情况
        return curRes;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
