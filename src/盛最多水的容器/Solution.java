package 盛最多水的容器;

/**
 * @author cwq
 * @since 2021/03/25
 * @link https://leetcode-cn.com/problems/container-with-most-water/
 */

public class Solution {

    public int maxArea(int[] height) {
        int res = 0, i = 0, j = height.length - 1;
        while (i < j) {
            res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            if(height[i] > height[j]) {
                j--;
            }
            else {
                i++;
            }
        }
        return res;
    }
}
