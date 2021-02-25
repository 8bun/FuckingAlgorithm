package 两个链表的第一个公共节点;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description：链表如果存在长度不同，在长的头节点开始先走若干步再一起遍历比较，直到第一个相同
 * @url：使用队列，也可以直接遍历计算长度，然后移动长链表的指针若干位
 * @限制：
 * @author：Jack
 * @createTime：2020/3/6 17:13
 * @level：
 */
public class Solution2 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Queue<ListNode> queueA=new LinkedList<>();
        Queue<ListNode> queueB=new LinkedList<>();
        while (headA!=null){
            queueA.offer(headA);
            headA=headA.next;
        }
        while (headB!=null){
            queueB.offer(headB);
            headB=headB.next;
        }
        int sizeA=queueA.size();
        int sizeB=queueB.size();
        if (sizeA>sizeB){
            for(int step=1;step<=sizeA-sizeB;step++){
                queueA.poll();
            }
        }
        else {
            for(int step=1;step<=sizeB-sizeA;step++){
                queueB.poll();
            }
        }
        ListNode same=null;
       while (!queueA.isEmpty()&&!queueB.isEmpty()){
           if(queueA.peek()==queueB.peek()){
                same=queueA.peek();
                break;
           }else {
               queueA.poll();
               queueB.poll();
           }
       }
        return same;
    }
}
