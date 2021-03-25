package 旋转链表;

import java.util.List;

/**
 * 循环旋转，其实本质上是将尾部向前数第K个元素作为头，原来的头接到原来的尾上
 * @author cwq
 * @since 2021/03/24
 * @link https://leetcode-cn.com/problems/rotate-list/
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

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) {
            return null;
        }
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            ++n;
            cur = cur.next;
        }
        k %= n;
        ListNode fast = head, slow = head;
        for (int i = 0; i < k; i++) {
            if(fast != null) {
                fast = fast.next;
            }
        }
        // 链表长不足k
        if(fast == null) {
            return head;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = head;
        fast = slow.next;
        slow.next = null;
        return fast;
    }
}
