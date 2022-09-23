package 合并K个升序链表;

public class Solution2 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 最先想到的就是两两合并，就是前两个先合并，合并好了再跟第三个，然后第四个直到第k个。这样的思路是对的，但是效率不高，没法通过 OJ，所以只能换一种思路，
     * 这里就需要用到分治法 Divide and Conquer Approach。简单来说就是不停的对半划分，比如k个链表先划分为合并两个 k/2 个链表的任务，再不停的往下划分，
     * 直到划分成只有一个或两个链表的任务，开始合并。举个例子来说比如合并6个链表，那么按照分治法，首先分别合并0和3，1和4，2和5。这样下一次只需合并3个链表，
     * 再合并1和3，最后和2合并就可以了。代码中的k是通过 (n+1)/2 计算的，这里为啥要加1呢，这是为了当n为奇数的时候，k能始终从后半段开始，
     * 比如当 n=5 时，那么此时 k=3，则0和3合并，1和4合并，最中间的2空出来。当n是偶数的时候，加1也不会有影响，比如当 n=4 时，此时 k=2，那么0和2合并，1和3合并，完美解决问题
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        int n = lists.length;
        while (n > 1) {
            int k = (n + 1) / 2;
            for (int i = 0; i < n / 2; i++) {
                lists[i] = mergeTwoList(lists[i], lists[i + k]);
            }
            n = k;
        }
        return lists[0];
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }
}
