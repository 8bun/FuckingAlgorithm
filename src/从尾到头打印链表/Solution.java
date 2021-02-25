package 从尾到头打印链表;

import java.util.Stack;

/**
 * @description:使用栈存储从头到尾的链表节点，然后输出实现反向输出
 * @url:https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 * @author:Jack
 * @createTime:2020/2/21 11:15
 * @level:简单
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Solution {
    public int[] reversePrint(ListNode head) {
        Stack<Integer> vals=new Stack<>();
        while(head!=null){
            vals.push(head.val);
            head=head.next;
        }
        int res[]=new int[vals.size()];
        int cnt=0;
        while (!vals.isEmpty()){
            res[cnt++]=vals.pop();
        }
        return res;
    }
}
