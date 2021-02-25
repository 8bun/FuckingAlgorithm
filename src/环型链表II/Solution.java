package 环型链表II;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/22 11:21
 * @Description: 给定链表，找出链表开始入环的第一个节点
 * 具体思路见数学推导.png
 * @Url: https://leetcode-cn.com/problems/linked-list-cycle-ii/
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
    public ListNode detectCycle(ListNode head) {
        if (head==null||head.next==null)
            return null;
        ListNode slow=head,fast=head;
        ListNode meetNode=null;
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if (fast==slow) {
                meetNode = fast;
                break;
            }
        }
        if (meetNode!=null) {
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
        return null;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
