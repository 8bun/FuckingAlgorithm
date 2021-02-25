package DFS系列.节点与其祖先之间的最大差值;

import sun.reflect.generics.tree.Tree;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/5 20:39
 * @Description:
 * @Url: https://leetcode-cn.com/problems/maximum-difference-between-node-and-ancestor/
 * @限制:
 * 树中的节点数在 2 到 5000 之间。
 * 每个节点的值介于 0 到 100000 之间。
 * @Level:
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Solution {
    private int max_sub=0;
    public int maxAncestorDiff(TreeNode root) {
        dfs(root,root.val,root.val);
        return max_sub;
    }

    /**
     * 对于每个路径维护一个当前最大值和最小值，实时更新max_sub
     * @param curNode
     * @param curMax
     * @param curMin
     */
    private void dfs(TreeNode curNode,int curMax, int curMin){
        if (curNode==null)return;
        curMax=Math.max(curNode.val,curMax);
        curMin=Math.min(curNode.val,curMin);
        max_sub=Math.max(Math.abs(curMax-curMin),max_sub);
        dfs(curNode.left,curMax,curMin);
        dfs(curNode.right,curMax,curMin);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
