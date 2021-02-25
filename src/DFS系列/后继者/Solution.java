package DFS系列.后继者;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/2 09:12
 * @Description: 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继)
 * @Url: https://leetcode-cn.com/problems/successor-lcci/
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
    /**
     * 从根结点root开始找第一个比p大的结点
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
       if (root==null)return null;
       //根据要查找的值进入范围内查找
       if (p.val<root.val) {
           TreeNode left = inorderSuccessor(root.left, p);//从根结点root.left开始找第一个比p大的结点
           if (left==null) //如果找不到，那么root就是第一个比p大的
               return root;
           else return left;//否则返回找到的结点
       }
       else return inorderSuccessor(root.right,p);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
