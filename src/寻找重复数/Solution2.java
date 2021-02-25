package 寻找重复数;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/21 23:28
 * @Description: 快慢指针做法
 * 将nums[i]=j看成i->next=j，所以如果出现了重复元素，那么链表一定会成环，利用
 * 快慢指针，同时从起点出发，慢指针一次走一步，快指针一次走两步，然后记录快慢指针相遇的点。
 * 之后再用两个指针，一个指针从起点出发，一个指针从相遇点出发，当他们再次相遇的时候就是入口点了。
 * [1,3,4,2]:
 * 142题环型链表II中慢指针走一步slow = slow.next ==> 本题 slow = nums[slow]
 * 142题中快指针走两步fast = fast.next.next ==> 本题 fast = nums[nums[fast]]
 * @Url:
 * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--52/
 * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/287xun-zhao-zhong-fu-shu-by-kirsche/
 * @限制:
 * @Level:
 */
public class Solution2 {
    public int findDuplicate(int[] nums) {
        int slow=nums[0];
        int fast=nums[nums[0]]; //nums[0]表示指向0的下一个数，nums[nums[0]]表示指向0的下一个数的下一个数
        while (slow!=fast){
            slow=nums[slow];  //slow = slow.next
            fast=nums[nums[fast]]; //fast = fast.next.next
        }
        //相遇之后，再用两个指针，一个指针从起点出发，一个指针从相遇点出发，当他们再次相遇的时候就是入口点了。
        //slow 从起点出发, fast 从相遇点出发, 一次走一步
        slow=0;
        while (slow!=fast){
            slow=nums[slow];
            fast=nums[fast];
        }
        return slow;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
