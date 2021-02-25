package 重排链表;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/22 12:45
 * @Description: 给定链表 1->2->3->[4]->[5], 重新排列为 1->[5]->2->[4]->3.
 * 通过观察，可以将重排链表分解为以下三个步骤：
 * 首先重新排列后，链表的中心节点会变为最后一个节点。所以需要先找到链表的中心节点：见LC876.链表的中间结点
 * 可以按照中心节点将原始链表划分为左右两个链表。
 * 2.1. 按照中心节点将原始链表划分为左右两个链表，左链表：1->2->3 右链表：4->5。
 * 2.2. 将右链表反转，就正好是重排链表交换的顺序，右链表反转：5->4。反转链表：见LC206.反转链表
 * 合并两个链表，将右链表插入到左链表，即可重新排列成：1->5->2->4->3.
 * 可以利用快慢指针找到中心结点
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution1 {

    public void reorderList(ListNode head) {
        if (head == null)
            return;
        ListNode middle = findMiddle(head);
        ListNode left = head;
        ListNode right = middle.next;
        middle.next = null;
        right = reverseList(right);
        merge(left, right);
    }

    /**
     * 按题目要求合并两个链表
     * 将右链表结点依次插入左链表的结点之间
     * @param left
     * @param right
     */
    private void merge(ListNode left, ListNode right) {

        while (right != null) {
            ListNode tmp = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = tmp;
        }
    }

    /**
     * 利用快指针的路程是慢指针的路程的2倍，找到中心结点
     * @param head
     *
     * @return
     */
    public ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode cur = null;
        ListNode pre = head;

        while (pre != null) {
            ListNode tmp = pre.next;
            pre.next = cur;
            cur = pre;
            pre = tmp;
        }
        return cur;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
