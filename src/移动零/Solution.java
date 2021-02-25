package 移动零;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/19 22:32
 * @Description:
 * 用一个慢指针去从头开始遍历下一个非零元素待填的位置，
 * 然后用一个快指针寻找下一个非零元素来填充，最后直接在后面停下的位置填充0即可
 * @Url: https://leetcode-cn.com/problems/move-zeroes/
 * @限制:
 * @Level:
 */
public class Solution {
    public void moveZeroes(int[] nums) {
        int slow=0,fast=0;
        for ( ;fast<nums.length;fast++){
            if (nums[fast]!=0)
                nums[slow++]=nums[fast];
        }
        for (;slow<nums.length;slow++)
            nums[slow]=0;
    }
    //优化版本：当我们遇到一个非零元素时，我们需要交换当前指针和慢速指针指向的元素，然后前进两个指针。如果它是零元素，我们只前进当前指针。
    //例如[0,3,0,0,1]
    //此时遇到0，那么slow=0,fast=1
    //此时遇到3，那么交换slow和fast指向的元素[3,0,0,0,1]，然后slow=1,fast=2
    //此时遇到0，那么slow=1,fast=3
    //直到遇到1，此时slow=1,fast=4,交换得到[3,1,0,0,0]
    //所以慢指针之前的所有元素都是非零的，当前指针和慢速指针之间的所有元素都是零
    //思想：slow指针永远指向的是下一个待填充非零数的位置，而fast是去找非零来填充到slow的位置
    //当fast遇到非零元素的时候，将其填补到slow的位置，也就是说要么fast跟slow重合，要么slow指向的位置是0,然后再隔了
    //若干个0才是fast指向的非零元素，所以这种算法不会改变非零元素的相对顺序
    public void moveZeroes1(int[] nums) {
        int slow=0,fast=0;
        for ( ;fast<nums.length;fast++){
            if (nums[fast]!=0){
                int tmp=nums[fast];
                nums[fast]=nums[slow];
                nums[slow]=tmp;
                slow++;
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
