package BFS系列.在每个树行中找最大值;

import com.sun.xml.internal.ws.api.addressing.AddressingVersion;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/5/5 11:37
 * @Description:
 * @Url: https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/
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
     * BFS做法
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        if (root==null)
            return res;
        Deque<TreeNode> deque=new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()){
            //开始进入同层遍历
            int size=deque.size();
            int max=Integer.MIN_VALUE;
            for (int i=0;i<size;i++){
                //注意，遍历同一层的所有节点时，在加入某节点的所有子节点之前，需要将自己出队
                TreeNode start=deque.pollFirst();
                max=Math.max(start.val,max);
                if (start.left!=null)
                    deque.addLast(start.left);
                if (start.right!=null)
                    deque.addLast(start.right);
            }
            res.add(max);
        }
        return res;
    }
    /**
     * DFS做法
     * 深度优先，先对根结点到叶子结点遍历一次，记录下每一层当前的最大值，然后再对根结点到叶子结点
     * 其他路径遍历，更新对应层的当前最大值
     * DFS会更快
     * @param root
     * @return
     */
    public List<Integer> largestValues1(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        if (root==null)
            return res;
        dfs(res,root,0);
        return res;
    }

    private void dfs(List<Integer> res,TreeNode root,int curLevel){ //当前root处于curLevel层
        if (root==null)
            return;
        //条件从res，curLevel,root想
        if (res.size()<=curLevel) //第一次到curLevel层，直接添加
            res.add(root.val);
        else {
            //更新当前层的最大值
            int max=Math.max(root.val,res.get(curLevel));
            res.set(curLevel,max);
        }
        dfs(res, root.left,curLevel+1);
        dfs(res,root.right,curLevel+1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
