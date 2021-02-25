package 零到n减1中缺失的数字;

/**
 * @description：
 * @url： https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/submissions/
 * @限制：1 <= 数组长度 <= 10000
 * @author：Jack
 * @createTime： 2020/3/6 18:11
 * @level：简单
 */
public class Solution {
    public int missingNumber(int[] nums) {
        //nums[]=[0,1,2]等连续的情况,返回下一个元素3
        int len=nums.length;
        if(nums[len-1]==len-1)
            return len;
        return search(0,len-1,nums);
    }

    /**
     * 二分法查找，如果num[mid]==mid，则mid左边都连续，右边不连续，下个查找区间[mid+1,r]
     * 不相等，则num[mid]的左边一定不连续，下个查找区间[l,mid]
     * @param l
     * @param r
     * @param nums
     * @return
     */
    private int search(int l,int r,int[] nums){

        if(l==r)
            return l;
        int mid=(l+r)>>1;
        if (nums[mid]==mid){
            return search(mid+1,r,nums);
        }
        else{
            return search(l,mid,nums);
        }
    }
}
