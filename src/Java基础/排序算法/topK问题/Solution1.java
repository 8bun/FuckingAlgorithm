package Java基础.排序算法.topK问题;

/**
 * @description：
 * 法一，用O(n(logn))的快排来取最大的k个，明明只需要TopK，却将全局都排序了，这也是这个方法复杂度非常高的原因
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/3 12:54
 * @level：
 */
public class Solution1 {
    public static void main(String[] args) {
        int[] nums={6,2,5,3,1,8,9,10,4,7};

        int k=5;
        QuickSort(nums,0,nums.length-1);
        for (int i=0;i<k;i++){
            System.out.print(nums[i]+" ");
        }
    }
    //时间复杂度：O(nlg(n))
    private static void QuickSort(int[] nums, int left,int right) {

//        int l=left;
//        int r=right;
//        if(l<r) {
//            int pivot = nums[left];
//            while (l != r) {
//                while (l < r && nums[r] >= pivot) {
//                    r--;
//                }
//                nums[l] = nums[r];
//                while (l < r && nums[l] <= pivot) {
//                    l++;
//                }
//                nums[r] = nums[l];
//            }
//            nums[l] = pivot;
//            QuickSort(nums, left, l - 1);
//            QuickSort(nums, l+1, right);
//        }
    }

}
