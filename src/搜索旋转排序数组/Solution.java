package 搜索旋转排序数组;

/**
 * @description：
 * @url：https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/ji-bai-liao-9983de-javayong-hu-by-reedfan/
 * @限制：
 * @author：Jack
 * @createTime：2020/4/12 10:32
 * @level：
 */
public class Solution {
    public int search(int[] nums, int target) {
        if (nums==null||nums.length==0)
            return -1;
        return find(0,nums.length-1,target,nums);
    }
    //723456
    private int find(int left,int right,int target,int[] nums){
        int mid=(left+right)/2;
        if(target==nums[mid])
            return mid;
        else {
            if(left+1==right)
                if (nums[right]==target)
                    return right;
                else return -1;
        }
        if(left<right) {
            if (nums[left] > nums[mid]) { //61234,(前有序的数量<后)关注后有序部分的情况，其余都在另一半查找
                if (target > nums[mid] && target <= nums[right])
                    return find(mid + 1, right, target, nums);
                return find(left, mid, target, nums);
            } else { //678912,(前有序的数量>后)关注左有序部分的情况，其余都在另一半查找
                if (target >= nums[left] && target < nums[mid])
                    return find(left, mid - 1, target, nums);
                return find(mid, right, target, nums);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int nums[]={4,5,6,7,8,19,1,2,3};
        int index=new Solution().search(nums,19);
        System.out.println(index);
    }
}
