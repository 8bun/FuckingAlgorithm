package shopee笔试;

import java.util.*;

/**
 * @author cwq
 * @since 2021/03/17
 */
public class Main1 {


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static Queue<TreeNode> nodeQueue = new LinkedList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        str = str.substring(1, str.length() - 1);
        TreeNode root = build(str);
        List<List<Integer>> lists = levelVisit(root);
        StringBuilder res = new StringBuilder();
        for (List<Integer> list : lists) {
            res.append("[");
            for(Integer i : list) {
                res.append(i).append(",");
            }
            res.deleteCharAt(res.length() - 1);
            res.append("]").append(",");
        }
        res.deleteCharAt(res.length() - 1);
        System.out.println("[" + res.toString() + "]");
    }
    private static List<List<Integer>> levelVisit(TreeNode root) {
        List<List<Integer>> levelLists = new ArrayList<>();
        if (root == null) {
            return levelLists;
        }
        List<TreeNode> levelList = new ArrayList<>();
        levelList.add(root);
        while (! levelList.isEmpty()) {
            int size = levelList.size();
            List<Integer> curLevelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = levelList.remove(0);
                curLevelList.add(node.val);
                if (node.left != null) {
                    levelList.add(node.left);
                }
                if (node.right != null) {
                    levelList.add(node.right);
                }
            }
            levelLists.add(curLevelList);
        }
        return levelLists;
    }

    private static TreeNode build(String str) {
        String[] strs = str.split(",");
        if (strs.length == 0 || str.equals("#")) {
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
            nodeQueue.add(root);
            int curIndex = 1;
            while (! nodeQueue.isEmpty() && curIndex < strs.length) {
                TreeNode treeNode = nodeQueue.poll();
                if (strs[curIndex].equals("#")) {
                    treeNode.left = null;
                    curIndex++;
                } else {
                    treeNode.left = new TreeNode(Integer.parseInt(strs[curIndex]));
                    nodeQueue.add(treeNode.left);
                    curIndex++;
                }
                if (curIndex < strs.length && strs[curIndex].equals("#")) {
                    treeNode.right = null;
                    curIndex++;
                } else if (curIndex < strs.length && ! strs[curIndex].equals("#")) {
                    treeNode.right = new TreeNode(Integer.parseInt(strs[curIndex]));
                    nodeQueue.add(treeNode.right);
                    curIndex++;
                }
            }
            return root;
        }
    }
}
