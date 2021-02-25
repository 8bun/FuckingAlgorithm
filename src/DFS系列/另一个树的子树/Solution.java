package DFS系列.另一个树的子树;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/7 17:08
 * @Description:
 * 从s的每个结点作为根结点判断其子树是不是与t是相同的树
 * @Url: https://leetcode-cn.com/problems/subtree-of-another-tree/
 * @限制:
 * @Level:
 */
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

public class Solution {
    /**
     * 判断从s的每个结点作为根结点判断其子树是不是与t是相同的树
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
       if (s==null&&t==null)return true;
       if (s==null||t==null)return false;
       return isSame(s,t) || isSubtree(s.left,t)|| isSubtree(s.right,t);
    }

    /**
     * 判断以t1、t2为根结点的树是否相同
     * @param t1
     * @param t2
     * @return
     */
    private boolean isSame(TreeNode t1, TreeNode t2){
        if (t1==null&&t2==null) //如果两棵树都为空树
            return true;
        if (t1==null||t2==null)
            return false;
        if (t1.val==t2.val)
            return isSame(t1.left,t2.left)&&isSame(t1.right,t2.right);
        else return false;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
