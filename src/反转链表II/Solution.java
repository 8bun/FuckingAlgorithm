package 反转链表II;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转
 * @author cwq
 * @link https://leetcode-cn.com/problems/reverse-linked-list-ii/
 * @since 2021/03/17
 */
public class Solution {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) { this.val = val; }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1), pre = dummy;
        dummy.next = head;
        // 最终让pre指向要反转的链表的第一个节点的前一个节点
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        for (int i = 0; i < right - left; i++) {
            // cur.next一定是需要头插的removed节点，而pre永远保持不变
            ListNode removed = cur.next;
            cur.next = removed.next;
            removed.next = pre.next;
            pre.next = removed;
        }
        return dummy.next;
    }
}
