package 排序链表;

/**
 * 归并排序
 * 在 O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序
 *
 * @author cwq
 * @link https://www.cnblogs.com/grandyang/p/4249905.html
 * @since 2021/03/24
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

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head, pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 断链
        pre.next = null;
        return merge(sortList(head), sortList(slow));
    }

    /**
     * 合并以l1、l2为头节点的有序链表，返回合并后的头节点
     */
    private ListNode merge(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // 越小的作为头
        if (l1.val < l2.val) {
            // 注意前面断链了，所以当l1,l2都只有一个节点时，此时l1.next为null，递归之后返回l2，之后l1，l2有序之后返回l1
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

}
