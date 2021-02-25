package 合并两个排序的链表;

/**
 * @description:
 * @url:https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
 * @author:Jack
 * @createTime:2020/2/25 20:03
 * @level:简单
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        //合并在一个新链表上
        ListNode newListNode=new ListNode(1);
        ListNode last=newListNode;  //合并链表的最后一个节点

        while (l1!=null&&l2!=null){
            if(l1.val < l2.val){//记录合并l1节点的最后一个节点l1，以及l1的下一个节点（为了下一次遍历），以及更新合并节点的最后一个节点
                last.next=l1;
                last=l1;
                l1=l1.next;
            }
            else{
                last.next=l2;
                last=last.next;
                l2=l2.next;
            }
        }
        last.next=l1 == null? l2 : l1; //把最后非空的链连接起来
        return newListNode.next;
    }
}
