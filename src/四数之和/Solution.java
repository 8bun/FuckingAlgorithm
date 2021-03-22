package 四数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cwq
 * @link https://leetcode-cn.com/problems/4sum/
 * @since 2021/03/19
 */
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len < 4) {
            return res;
        }
        Arrays.sort(nums);
        // 第1个元素的范围
        for (int first = 0; first < len - 3; first++) {

            // 去重
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // 第2个元素范围
            for (int second = first + 1; second < len - 2; second++) {
                // 去重
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                int tarSum = target - nums[first] - nums[second];
                // 在剩下的右边的数，找到和为tarSum的两个数
                int left = second + 1, right = len - 1;
                while (left < right) {
                    int curSum = nums[left] + nums[right];
                    if (curSum > tarSum) {
                        right--;
                    }
                    else if(curSum < tarSum) {
                        left++;
                    }
                    else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[first]);
                        list.add(nums[second]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        res.add(list);
                        // 这里是一个细节，需要再次去重，因为可能出现[2,2,3,3]，当left指向0，right指向3
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }
        return res;
    }
}
