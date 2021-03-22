package K个一组翻转链表;

/**
 * @author cwq
 * @link https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * https://zhuanlan.zhihu.com/p/90170262
 * @since 2021/03/09
 */
public class Solution {

    /**
     * @param head 当前链表的头结点
     * @param k    反转头结点（包括）到后面的k个结点
     * @return 返回从头节点开始反转后的链表(未被拆)的头结点
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            //不足 k 个，不需要反转，base case
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        //反转[a,b)的链表，之所以是开区间，是因为前面b=b.next在最后一次循环的作用
        //所以此时newHead在未拆链（因为反转被拆）的时候应该是newHead.next = b;
        //所以a作为末尾要指向b开始的才能连起来
        ListNode newHead = reverse(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    /**
     * 反转从[a,b)的链表，注意此时还未拆链
     * @param a
     * @param b
     * @return 返回反转的子链表的头结点
     */
    private ListNode reverse(ListNode a, ListNode b) {

        ListNode pre = null, cur = a, tmp;
        while (cur != b) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        // 返回反转后的头结点
        return pre;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) { this.val = val; }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
