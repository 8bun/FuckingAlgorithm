package 重排链表;

import java.util.Scanner;
import java.util.Stack;

/**
 * @Author: Jack
 * @Date: 2020/4/22 12:01
 * @Description: 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 先把链表元素全部入栈，然后一边从头遍历，一边出栈。遍历一半、出栈一半
 * 这种做法有点缺点就是栈里面有一半的没有用到，栈的长度一长就比较慢
 * @Url: https://leetcode-cn.com/problems/reorder-list/
 * @限制: 注意：单链表无法快速访问到末尾结点，所以遍历头尾结点插入的方法是无效的
 * @Level:
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class Solution {

    public void reorderList(ListNode head) {

        Stack<ListNode> stack = new Stack<>();
        ListNode tmp = head;
        while (tmp != null) {
            stack.add(tmp);
            tmp = tmp.next;
        }
        if (stack.size() <= 2)
            return;
        //一边遍历链表，一边遍历栈
        int time = stack.size() / 2;
        ListNode ptr = head;
        ListNode ptr_next = ptr.next;
        for (int i = 0; i < time; i++) {
            ListNode to_In = stack.pop();
            to_In.next = ptr_next;
            ptr.next = to_In;
            ptr = to_In.next;
            ptr_next = ptr.next;
        }
        ptr.next = null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
