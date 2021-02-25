package 序列化二叉树;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 实现二叉树的toString()以及通过String反向解二叉树，时间需优化，可使用递归
 * @url: https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/
 * @author: Jack
 * @createTime: 2020/2/29 22:16
 * @level: 困难
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //通过层序遍历二叉树，并逐步toString
        if(root==null)
            return "[]";
        Queue<TreeNode> queue=new LinkedList<>();
        String res="[";
        queue.add(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++) {
                TreeNode node=queue.poll();
                if(node==null)
                    res+="null,";
                else{
                    res+=node.val+",";
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
        }
        res=res.substring(0,res.length()-1); //去除最后的,
        return res+"]";
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data==null||data.equals("[]"))
            return null;
        String[] vals=data.substring(1,data.length()-1).split(",");
        int index=0;
        TreeNode root=new TreeNode(Integer.parseInt(vals[index++]));  //根结点
        Queue<TreeNode> queue=new LinkedList<>(); //使用队列进行二叉树的建立
        queue.add(root);
        while (!queue.isEmpty()){
            //每次遍历都是有内容的结点
            TreeNode node=queue.poll();
            node.left=buildTreeNode(vals[index++]);
            node.right=buildTreeNode(vals[index++]);
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
        return root;
    }
    private TreeNode buildTreeNode(String val){
        if("null".equals(val)){
            return null;
        }
        return new TreeNode(Integer.valueOf(val));
    }

    public static void main(String[] args) {

    }
}
