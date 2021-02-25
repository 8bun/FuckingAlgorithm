package 所有蚂蚁掉下来前的最后一刻;
/**
 * @Author: Jack
 * @Date: 2020/7/5 15:13
 * @Description:
 * left[]数组代表的是往左边移动的蚂蚁的初始位置，
 * right[]数组代表的是往右边移动的蚂蚁的初始位置
 * 题目说，当木板上的两只方向不同的蚂蚁相遇时，两只蚂蚁变换方向，继续移动，直到掉下木板。
 * 那么换个思路想，两只蚂蚁变换方向继续移动不就是原先两只蚂蚁谁都不理谁，然后继续原方向移动嘛。
 * 虽然你可能会说，此时往右移动的蚂蚁是原先往左移动的蚂蚁，但是我的思想是往右移动的蚂蚁还是原先往右移动的蚂蚁，
 * 往左移动的蚂蚁还是原先往左移动的蚂蚁，虽然没有发生改变，但是你可以认为方向改变后，我又把他们的名字换了，那么就是没有改变。
 * 因为题目要求的是最后掉下来蚂蚁的时刻，跟哪只蚂蚁没有任何关系，换个名字也没有什么。并且在运算时，
 * 往右移动的蚂蚁和往左移动的蚂蚁数量最后并没有发生增多和减少的现象，跟原先是一样的，
 * 所以我们可以认为最后往右移动的是当初给定的蚂蚁，往左移动的也是如此.
 * 那么我们就判断往右移动蚂蚁的下标离木板的最右端的距离 与 往左移动蚂蚁的下标离木板最左端的距离的最大值。即答案
 * @Url: https://leetcode-cn.com/problems/last-moment-before-all-ants-fall-out-of-a-plank/
 * @限制: 
 * @Level: 
 */
public class Solution {

    public int getLastMoment(int n, int[] left, int[] right) {
        int ans = 0;
        for (int l : left) ans = Math.max(l, ans);
        for (int r : right) ans = Math.max(n - r, ans);
        return ans;
    }

    public static void main(String[] args){
    }
}
