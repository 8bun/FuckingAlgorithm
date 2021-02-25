package 二叉搜索树与双向链表;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 将该二叉搜索树转换成一个“排序”（所以是中序遍历）的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * @url: https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 * @author: Jack
 * @createTime: 2020/2/29 12:57
 * @level: 中等
 */
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
public class Solution {

    private List<Node> inOrderNodeList=new ArrayList<>();

    public Node treeToDoublyList(Node root) {

        if(root==null)
            return null;
        InOrder(root);
        Node first=inOrderNodeList.get(0);
        Node ptr=first;
        for(int i=1;i<inOrderNodeList.size();i++){
            ptr.right=inOrderNodeList.get(i);
            inOrderNodeList.get(i).left=ptr;
            ptr=ptr.right;
        }
        //头尾相接
        ptr.right=first;
        first.left=ptr;
        return first;
    }
    //中序遍历，初始化inOrderNodeList
    private void InOrder(Node node){
        if(node==null){
            return;
        }
        if(node.left!=null) InOrder(node.left);
        inOrderNodeList.add(node);
        if(node.right!=null) InOrder(node.right);
    }
}
