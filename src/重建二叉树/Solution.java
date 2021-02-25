package 重建二叉树;

import sun.reflect.generics.tree.Tree;

import java.sql.PreparedStatement;
import java.util.HashMap;

/**
 * @description: 递归重建，从前序遍历找子树根节点，再在中序遍历中以根节点找到其左右子树，
 * 再在左右子树中递归查找根节点..
 * @url:https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
 * @author:Jack
 * @createTime:2020/2/21 12:06
 * @level:中等
 */
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
public class Solution {

    private HashMap<Integer,Integer> prePlace;
    private int[] preorder;
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        this.preorder=preorder;
        prePlace=new HashMap<>(inorder.length);
        for(int i=0;i<inorder.length;i++)
            prePlace.put(inorder[i],i);
        return Find(0,preorder.length-1,0,inorder.length-1);

    }
    //分别表示递归时的同一侧子树前序遍历和中序遍历的数组下标边界
    private TreeNode Find(int preL,int preR,int inL,int inR){

        //递归调用结束条件
        if(preL>preR||inL>inR)
            return null;
        //子树根节点值
        int root_val=preorder[preL];

        //子树根节点在中序遍历序列中的位置
        int mid=prePlace.get(root_val);

        TreeNode root=new TreeNode(root_val);
        //推导：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/solution/er-cha-shu-de-qian-xu-bian-li-fen-zhi-si-xiang-by-/
        root.left=Find(preL+1,mid-inL+preL,inL,mid-1);
        root.right=Find(mid-inL+preL+1,preR,mid+1,inR);
        return root;
    }
}
