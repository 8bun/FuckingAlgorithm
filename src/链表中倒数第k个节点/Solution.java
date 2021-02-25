package 链表中倒数第k个节点;

import java.util.List;

/**
 * @description:定义两个指针，快指针 fast， 慢指针 low .
 * 让 fast先向前移动 kk 个位置，然后 low 和 fast 再一起向前移动 .
 * 当 fast 到达链表尾部，(这样就保证了fast与low相距k个节点)返回 low.
 * @url:https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 * @author:Jack
 * @createTime:2020/2/25 19:36
 * @level:简单
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Solution {

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast=head;
        ListNode low=head;
        for(int i=0;i<k;i++)
            fast=fast.next;
        while (fast!=null){
            low=low.next;
            fast=fast.next;
        }
        return low;
    }
}
