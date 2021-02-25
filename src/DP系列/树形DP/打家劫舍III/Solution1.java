package DP系列.树形DP.打家劫舍III;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/13 23:44
 * @Description:
 * 针对解法一种速度太慢的问题，经过分析其实现，我们发现爷爷在计算自己能偷多少钱的时候，同时计算了 4 个孙子能偷多少钱，
 * 也计算了 2 个儿子能偷多少钱。这样在儿子当爷爷时计算自己能偷多少钱的时候，就会重复计算一遍（自己的儿子，也就是自己的爷爷的孙子）
 * 孙子节点能偷多少钱
 * 我们这一步针对重复子问题进行优化，我们在做斐波那契数列时，使用的优化方案是记忆化，
 * 但是之前的问题都是使用数组解决的，把每次计算的结果都存起来，下次如果再来计算，就从缓存中取，不再计算了
 * ，这样就保证每个数字只计算一次。
 * 由于二叉树不适合拿数组当缓存，我们这次使用哈希表来存储结果，TreeNode 当做 key，能偷的钱当做 value
 * @Url:
 * @限制:
 * @Level:
 */

public class Solution1 {

    private HashMap<TreeNode, Integer> map = new HashMap<>();
    public int rob(TreeNode root) {
        if (root == null) return 0;

        if (map.containsKey(root)) //如果这个节点已经算过它能够偷多少钱
            return map.get(root);

        int money = root.val;
        if (root.left != null) money += rob(root.left.left) + rob(root.left.right);
        if (root.right != null) money += rob(root.right.left) + rob(root.right.right);

        int maxSteal = Math.max(money, rob(root.left) + rob(root.right));
        map.put(root, maxSteal);
        return maxSteal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
