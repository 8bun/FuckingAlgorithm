package 从先序遍历还原二叉树;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/7 10:49
 * @Description:
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。(先序遍历)
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。
 * （如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 * "如果节点只有一个子节点，那么保证该子节点为左子节点。"
 * 给出遍历输出 S，还原树并返回其根节点 root。
 * @Url: https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal/
 * https://www.bilibili.com/video/BV1Tb411L7en?from=search&seid=16550114936367150864
 * @限制:
 * @Level:
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

/**
 * 模拟遍历过程建树
 */
public class Solution {
    private TreeNode[] root_Node;  //保存某depth层当前的root结点
    private String[] valArr;  //保存结点值,以""的个数+1作为""后遇到的非空val的depth
    public TreeNode recoverFromPreorder(String S) {
        valArr= S.split("-");
        root_Node=new TreeNode[valArr.length];
        TreeNode root=new TreeNode(Integer.parseInt(valArr[0]));
        root_Node[0]=root;
        for (int i=1;i<valArr.length;i++){
            int depth=1;
            while (valArr[i].length()==0){
                depth++;i++;
            }
            TreeNode parent=root_Node[depth-1];
            TreeNode newNode=new TreeNode(Integer.parseInt(valArr[i]));
            root_Node[depth]=newNode;
            if (parent.left==null)
                parent.left=newNode;
            else parent.right=newNode;
        }
        return root;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeNode root=new Solution().recoverFromPreorder("1-2--3--4-5--6--7");
        //valArr={1,2,"",3,"",4,5,"",6,"",7}
        System.out.println(root);
    }
}
