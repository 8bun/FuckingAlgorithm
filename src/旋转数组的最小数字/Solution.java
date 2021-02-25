package 旋转数组的最小数字;

/**
 * @description:两个有序递增序列的边界，左右的规律是不一样的
 * 例如451234，512，是先减后增，所以得到最小值1
 * 通过分析：如果使用二分法查找最小值（往往在右边），当分析到中间元素大于右边元素时，表明
 * 中间元素处于左递增分区，而右边元素处于右递增分区，那么由于最小值处于右递增分区，所以，缩小
 * 检查范围为[mid+1,right]；反之，如果中间元素小于右边元素时那么中间元素与右边元素都处于右递增分区
 * 最小值可能处于中间元素左边或者就是中间元素本身，检查范围为[left,mid]
 * 如果相等，那么无法判断处于哪个分区，但是可以判断最小值一定处于最右边元素的左边
 * 检查范围为[left,right-1]
 * @url:https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 * @author:Jack
 * @createTime:2020/2/21 20:07
 * @level:
 */
public class Solution {

    private int[] numbers;
    public int minArray(int[] numbers) {
        if (numbers.length==0)
            return -1;
        this.numbers=numbers;
        return Find(0,numbers.length-1);
    }
    private int Find(int left_index,int right_index){
        if(left_index==right_index)
            return numbers[left_index];
        int mid=(left_index+right_index)/2;
        if(numbers[mid]>numbers[right_index]){
            return Find(mid+1,right_index);
        }
        else if(numbers[mid]<numbers[right_index]){
            return Find(left_index,mid);
        }
        else return Find(left_index,right_index-1);
    }

    public static void main(String[] args) {
        int[] a={2,2,2,0,1};
        System.out.println(new Solution().minArray(a));
    }
}
