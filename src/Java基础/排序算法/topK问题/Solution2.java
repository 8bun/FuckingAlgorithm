package Java基础.排序算法.topK问题;

/**
 * @description：
 * 冒泡是一个很常见的排序方法，每冒一个泡，找出最大值(或最小值)，冒k个泡，就得到TopK。
 * 局部排序
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/3 12:57
 * @level：
 */
public class Solution2 {
    public static void main(String[] args) {
        int[] nums={6,2,5,3,1,8,9,10,4,7,855,-1,234};

        int k=7;
        BubbleSort(nums,k);
        for (int i=nums.length-1,cnt=0;cnt<k;cnt++,i--){
            System.out.print(nums[i]+" ");
        }
//        System.out.println();
//        for (int i=0;i<nums.length;i++)
//            System.out.print(nums[i]+" ");
    }

    private static void BubbleSort(int[] nums,int k) {
        for (int i=0;i<k;i++){
            for (int j=0;j<nums.length-i-1;j++){  //时间复杂度O(n*k)
                if(nums[j]>nums[j+1]){  //根据关系可调整冒小泡还是大泡
                    int tmp=nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=tmp;
                }
            }
        }
    }
}
