package 多数元素;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/16 00:04
 * @Description: 摩尔投票法，在区间a[0]...a[i]中，使用一个选举一个元素作为当前众数的候选者
 * 然后进行遍历，遇到跟它相同的元素，它的票数+1，遇到不同的-1，直到它的票数为0，表示此时它的票数（跟它一样的元素）
 * 等于了它的反对票。此时更换下一个候选者，遍历到最后的候选者一定有大于n/2的票数
 * https://leetcode-cn.com/problems/majority-element/solution/duo-shu-yuan-su-by-leetcode-solution/
 * @Url: https://leetcode-cn.com/problems/majority-element/
 * @限制:
 * @Level:
 */
public class Solution {

    public int majorityElement(int[] nums) {
        int candidate_num=nums[0],count=1;
        for (int i=1;i<nums.length;i++){
            if(candidate_num==nums[i])
                count++;
            else{
                if (--count==0){
                    candidate_num=nums[i];
                    count=1;
                }
            }
        }
        return candidate_num;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
