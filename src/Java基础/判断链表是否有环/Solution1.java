package Java基础.判断链表是否有环;

/**
 * @description：
 * 快慢指针，首先创建两个指针1和2（在java里就是两个对象引用），
 * 同时指向这个链表的头节点。然后开始一个大循环，在循环体中，让指针1每次向下移动一个节点，让指针2每次向下移动两个节点，
 * 然后比较两个指针指向的节点是否相同。如果相同，则判断出链表有环，如果不同，则继续下一次循环。
 * fast和slow相遇了，可以肯定的是这两个指针肯定是在环上相遇的。第一次相遇后，继续按照2 1的步数走，再次相遇时，
 * slow指针走的步数为环的长
 * @url：
 * @限制：
 * @author：Jack
 * @createTime： 2020/4/3 14:45
 * @level：
 */
class ListNode<T>{
    T val;
    ListNode next;
}
public class Solution1 {
    /**
     * 判断单链表是否存在环
     * @param head
     * @return
     */
    public static <T> boolean isLoopList(ListNode<T> head){
        ListNode<T> slowPointer, fastPointer;

        //使用快慢指针，慢指针每次向前一步，快指针每次两步
        slowPointer = fastPointer = head;
        while(fastPointer != null && fastPointer.next != null){
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            //两指针相遇则有环
            if(slowPointer == fastPointer){
                return true;
            }
        }
        return false;
    }

}
