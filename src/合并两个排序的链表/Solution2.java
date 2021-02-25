package 合并两个排序的链表;

/**
 * @description:采用递归的方式
 * @url:
 * @author:Jack
 * @createTime:2020/2/25 23:13
 * @level:
 */

public class Solution2 {

    //通过递归不断更新l1和l2节点，每次将比较这两个节点的大小，并返回较小的节点
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        //如果某个节点为null，则表示该链结束，并将非空节点作为较小节点返回作为合并链表的下一个节点
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        //如果l1节点较小，那么l1的下一个节点与l2比较大小并返回较小的节点作为l1的下一个节点并返回l1
       if(l1.val<=l2.val){
            l1.next=mergeTwoLists(l1.next,l2);
            return l1;
       }
        l2.next=mergeTwoLists(l1,l2.next);
        return l2;
    }
}
