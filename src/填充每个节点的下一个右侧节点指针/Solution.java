package 填充每个节点的下一个右侧节点指针;

/**
 * @author cwq
 * @since 2021/03/19
 */

public class Solution {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    public Node connect(Node root) {
        if(root == null) {
            return null;
        }
        connectTwoNodes(root.left, root.right);
        return root;
    }

    private void connectTwoNodes(Node node1, Node node2) {
        // 初始状态下，所有 next 指针都被设置为 NULL。
        if(node1 == null || node2 == null) {
            return;
        }
        node1.next = node2;
        connectTwoNodes(node1.left, node1.right);
        connectTwoNodes(node2.left, node2.right);
        connectTwoNodes(node1.right, node2.left);
    }
}
