package 合并K个升序链表;

import java.util.PriorityQueue;

/**
 * https://grandyang.com/leetcode/23/
 * 最小堆做法：
 * 首先把k个链表的首元素都加入最小堆中，它们会自动排好序。然后每次取出最小的那个元素加入最终结果的链表中，然后把取出元素的下一个元素再加入堆中，
 * 下次仍从堆中取出最小的元素做相同的操作，以此类推，直到堆中没有元素了，此时k个链表也合并为了一个链表，返回首节点即可
 */
public class Solution1 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        // 使用无参构造，元素在队列中默认按照从小到大的顺序排列，可保证每次出队列的元素为队列中的最小元素。
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            // o1.val < o2.val: less，自然排序
            return o1.val - o2.val;
        });
        // PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Collections.reverseOrder()); // 最大堆
        for (ListNode ld : lists) {
            // 只是加的头节点
            if (ld != null) {
                priorityQueue.add(ld);
            }
        }

        // 临时节点，后面返回使用
        ListNode dumpHead = new ListNode(0);
        ListNode cur = dumpHead;

        while (!priorityQueue.isEmpty()) {
            ListNode next = priorityQueue.poll();
            cur.next = next;
            cur = cur.next;

            // 提前把下个非空节点加进来
            if (cur.next != null) {
                priorityQueue.add(cur.next);
            }
        }
        return dumpHead.next;
    }
}
