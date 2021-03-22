package 删除链表的节点;

/**
 * @description:双指针解决删除节点
 * @link  https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
 * @author  cwq
 * @createTime:2020/2/24 11:29
 * @level:简单
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) { val = x; }
}

public class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode pre = head;
        ListNode after = head.next;

        //如果是第一个节点
        if (head.val == val) {
            head = head.next;
            return head;
        }
        while (after != null) {
            if (after.val == val) {
                pre.next = after.next;
                break;
            }
            pre = pre.next;
            after = after.next;
        }
        return head;
    }
}
