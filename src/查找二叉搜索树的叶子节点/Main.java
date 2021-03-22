package 查找二叉搜索树的叶子节点;


import java.util.Scanner;

/**
 * 首先我们要知道什么是二叉查找树，就是每一个节点的左子树都要小于它，它的右子树都要大于它。
 * 又因为是前序遍历的顺序。我们知道第一个点肯定是根节点，那么我们就找数组里面第一个大于它的元素，
 * 第一个大于它的点的右边就是它的右子树，而从它开始到第一个大于它的节点部分就是它的左子树，
 * 我们递归的遍历遍历这个过程，当左边界等于右边界时，代表当前元素已经没有孩子了，直接输出这个结点即可。
 * 注意一下边界情况即可。
 * @author cwq
 * @since 2021/03/17
 * @link https://www.nowcoder.com/question/next?pid=28761331&qid=1382579&tid=42153235
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");
        int len = s.length;
        int[] a = new int[len];
        for (int i = 0; i < len; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
        dfs(0, len - 1, a);
    }
    /**
     * 从[start, end]查找第一个大于target的数的下标，如果找不到，返回end+1
     * @param start
     * @param end
     * @param target
     * @param a
     * @return
     */
    private static int findIndex(int start, int end, int target, int[] a) {
        int i;
        for(i = start; i <= end; i++) {
            if(a[i] > target) {
                return i;
            }
        }
        return i;
    }

    /**
     * 在[start, end]内递归找叶子节点
     * @param start
     * @param end
     */
    private static void dfs(int start, int end, int[] a) {
        if(start > end) {
            return;
        }
        if(start == end) {
            System.out.print(a[start] + " ");
            return;
        }
        int index = findIndex(start + 1, end, a[start], a);
        if(index <= end) {
            // 递归左子树
            dfs(start + 1, index - 1, a);
            // 递归右子树
            dfs(index, end, a);
        }
        else {
            // 只有左子树
            dfs(start + 1, end, a);
        }
    }

}
