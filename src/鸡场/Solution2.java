package 鸡场;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 已知m>n
 * 待研究https://blog.csdn.net/pfdvnah/article/details/105332456
 */
public class Solution2 {
    public static int chickenFarm(int n, int m, int k, int[] a) {
        // PriorityQueue默认是小顶堆，此处将参数改为大顶堆
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        // 保存养鸡场原始的鸡的数量
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            queue.offer(a[i]);
            sum += a[i];
        }
        // 每个养鸡场增加的鸡数
        int incremental = 0;
        for (int i = 0; i < m; ++i) {
            incremental += k;
            // 鸡最多的养鸡场的鸡数
            int max = queue.poll();
            System.out.print("删除最大="+max);
            // 这个养鸡场当天卖掉的鸡数，+优先级高于>>
            int reduced = max + incremental + 1 >> 1;
            System.out.print(" 减少="+reduced);
            // 剩余的鸡数放回堆里
            queue.offer(max - reduced);
            System.out.println(" 重新加入="+reduced);
            // 原始的鸡数减少
            sum -= reduced;
        }
        // 此时 incremental = k × m，sum表示原始的剩余的鸡数
        return incremental * n + sum;
    }

    public static void main(String[] args) {
        System.out.println(chickenFarm(4,4,100,new int[]{1,1000,200,50}));
    }
}
