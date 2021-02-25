package 跳跃游戏;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/15 17:12
 * @Description: 贪心
 * 贪心规律：
 * 想要判断最终是否能够跳跃到终点，最快的办法即选择每次跳跃获取到的跳跃范围中最大的跳跃点；
 * @Url: https://leetcode-cn.com/problems/jump-game/
 * 题解： https://leetcode-cn.com/problems/jump-game/solution/tiao-yue-you-xi-by-leetcode-solution/
 * @限制:
 * @Level:
 */
public class Solution {

    public boolean canJump(int[] nums) {
        return canJump(nums,0,nums.length,0);
    }

    /**
     *
     * @param nums
     * @param cur_index
     * @param len
     * @param max_pos 最大能到的下标
     * @return
     */
    public boolean canJump(int[] nums,int cur_index,int len,int max_pos) {
        if(max_pos>=len-1)
            return true;
        if(cur_index==max_pos&&nums[max_pos]==0)  //如果最大能到0的位置，并且前面的跳不过0
            return false;
        return canJump(nums,cur_index+1,len,Math.max(max_pos,nums[cur_index]+cur_index));
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[] nums={3,2,1,0,4};
        boolean r=new Solution().canJump(nums);
        System.out.println(r);
    }
}
