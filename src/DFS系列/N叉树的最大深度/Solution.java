package DFS系列.N叉树的最大深度;

import java.util.List;
import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/5/5 20:20
 * @Description:
 * @link
 * @限制:
 * @Level:
 */

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

public class Solution {
    private int maxDepth = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }

    public int maxDepth(Node root) {
        if (root == null) return maxDepth;
        return dfs(root, 1);
    }

    private int dfs(Node root, int depth) {
        if (root.children.size() == 0)
            return depth;
        for (Node node : root.children) {
            maxDepth = Math.max(dfs(node, depth + 1), maxDepth);
        }
        return maxDepth;
    }
}
