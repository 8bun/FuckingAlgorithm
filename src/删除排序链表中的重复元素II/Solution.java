package 删除排序链表中的重复元素II;

/**
 * @author cwq
 * @since 2021/03/25
 * @link https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/comments/
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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        if(head.val != next.val) {
            head.next = deleteDuplicates(head.next);
            return head;
        }
        else {
            ListNode notDup = head.next.next;
            while (notDup != null && notDup.val == head.val) {
                notDup = notDup.next;
            }
            return deleteDuplicates(notDup);
        }
    }
}
