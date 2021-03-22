package 三数之和;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 1.将数组排序
 * 2.定义三个指针，i，j，k。遍历i，那么这个问题就可以转化为在i之后的数组中寻找nums[j]+nums[k]=-nums[i]这个问题，
 * 也就将三数之和问题转变为二数之和---（可以使用双指针）
 * 为什么要往后找，例如排序后的数组为-2 -1 3，当遍历到-2时，往后找到[-2,-1,3]满足和为0，在遍历到3时，如果还往前找的话就会重复[3,-2,-1]。
 * @author cwq
 * @since 2021/03/17
 * @link https://leetcode-cn.com/problems/3sum/
 */
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(nums);
        int n = nums.length;
        if(n < 3) {
            return result;
        }
        for (int i = 0; i < n; i++) {

            // nums[i - 1]已经遍历过了，下一个不能再遍历重复的
            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            int j = i + 1, k = n - 1;
            while (j < k) {
                if(nums[j] + nums[k] > target) {
                    k--;
                }
                else if (nums[j] + nums[k] < target) {
                    j++;
                }
                // find a 三元组
                else {
                    LinkedList<Integer> ans = new LinkedList<>();
                    ans.add(nums[i]);
                    ans.add(nums[j]);
                    ans.add(nums[k]);
                    result.add(ans);
                    // 找到第一个与当前不重复的nums[j]、nums[k]，注意数组是排好序的
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    // 因为前面的判断条件退出时，j k都需要再移动一次才能与当前不重复
                    j++;
                    k--;
                }
            }
        }
        return result;
    }
}
