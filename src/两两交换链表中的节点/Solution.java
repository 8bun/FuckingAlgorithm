package 两两交换链表中的节点;

/**
 * @author cwq
 * @link https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * @since 2021/03/25
 */

public class Solution {
    private class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) { this.val = val; }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode t = head.next;
        head.next = swapPairs(head.next.next);
        t.next = head;
        return t;
    }
}
