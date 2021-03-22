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
        return null;
    }
}
