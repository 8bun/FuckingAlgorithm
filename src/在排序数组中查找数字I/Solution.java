package 在排序数组中查找数字I;

/**
 * @description：统计一个数字在排序数组中出现的次数。
 * 先用二分法找到一个target数字出现的任意一个位置，然后两个指针一个左寻，一个右寻
 * @url：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
 * @限制：0 <= 数组长度 <= 50000
 * @author：Jack
 * @createTime：2020/3/6 17:35
 * @level：简单
 */
public class Solution {


    public int search(int[] nums, int target) {
        int len=nums.length;
        int cnt=0;
        if(len==0)
            return cnt;
        int start=f(0,len-1,nums,target);
        if(start==-1)
            return cnt;
        cnt++;
        //两个指针，一个左寻，一个右寻
        int left=start-1;
        int right=start+1;
        while (left>=0){
            if(nums[left--]==target)
                cnt++;
            else break;
        }
        while (right<=nums.length-1){
            if (nums[right++]==target)
                cnt++;
            else break;
        }
        return cnt;
    }
    private int f(int l,int r,int[] nums,int target){

        if(l==r&&nums[l]!=target)
            return -1;
        int mid=(l+r)>>1;
        if(target==nums[mid])
            return mid;  //返回位置
        else if(target<nums[mid]){
            return f(l,mid,nums,target);
        }
        else {
            return f(mid+1,r,nums,target);
        }
    }

    public static void main(String[] args) {
        int[] nums =new int[]{5,7,7,8,8,10};
        int target=10;

        System.out.println(new Solution().search(nums,target));
    }
}
