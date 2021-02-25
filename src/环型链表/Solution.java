package 环型链表;

import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/22 10:50
 * @Description:
 * 判断链表是否有环
 * 链表：快指针速度2，慢指针速度1，当都进入环之后，他们就一定会相遇
 * @Url: https://leetcode-cn.com/problems/linked-list-cycle/
 * @限制:
 * @Level:
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

    public boolean hasCycle(ListNode head) {

        if (head==null||head.next==null)
            return false;
        ListNode slow=head;
        ListNode fast=head;
        while (fast!=null&&fast.next!=null){ //fast.next.next;这个的保证
            fast=fast.next.next;
            slow=slow.next;
            if (slow==fast)
                return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
