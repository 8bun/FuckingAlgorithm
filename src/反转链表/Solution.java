package 反转链表;

/**
 * @description: 反转链表, 使用前后双指针解决，初始left = NULL,right=head，每次当right！=NULL时，将right.next=left
 * https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/solution/fan-zhuan-lian-biao-yi-dong-de-shuang-zhi-zhen-jia/
 * @url: https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
 * @author: Jack
 * @createTime:2020/2/25 19:46
 * @level: 简单
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {

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
}
