package 两个链表的第一个公共节点;

import java.util.Stack;

/**
 * @description： 如果两条链表有相交的节点，
 * 那么两个链表自第一个相交节点开始到最末尾节点这一部分一定会是重合的，让
 * 通过栈从头到尾部依次压入链表所有元素，然后同时取出判断到第一次不同的前一个结点
 * @url： https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
 * @限制：
 * @author：Jack
 * @createTime： 2020/3/6 14:58
 * @level： 简单
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Stack<ListNode> StackA = new Stack<>();
        Stack<ListNode> StackB = new Stack<>();

        while (headA != null) {
            StackA.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            StackB.add(headB);
            headB = headB.next;
        }
        ListNode same = null;
        while (! StackA.isEmpty() && ! StackB.isEmpty()) {
            if (StackA.peek() == StackB.peek()) {  //地址相同才能相交，而不是值相同
                same = StackA.peek();
                StackA.pop();
                StackB.pop();
            } else break;
        }
        return same;
    }
}
