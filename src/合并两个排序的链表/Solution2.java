package 合并两个排序的链表;

/**
 * @description:采用递归的方式
 * @link
 * @author  cwq
 * @createTime:2020/2/25 23:13
 * @level:
 */

public class Solution2 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        if(l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
