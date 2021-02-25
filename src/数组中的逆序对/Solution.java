package 数组中的逆序对;

/**
 * @description： 归并排序的过程：https://www.cnblogs.com/chengxiao/p/6194356.html
 * 将数组分为两部分，然后对两个数组分别又进行分解，知道分解成一个个的元素
 * 然后对
 * @url：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 * @限制：0 <= 数组长度 <= 50000
 * @author：Jack
 * @createTime：2020/3/5 11:20
 * @level：困难
 */
public class Solution {

//    public int reversePairs(i nt[] nums) {
//        //暴力法,超时,O(n^2)
//        int len=nums.length;
//        int cnt=0;
//        for(int i=0;i<len-1;i++){
//            for(int j=i+1;j<len;j++){
//                if(nums[i]>nums[j])
//                    cnt++;
//            }
//        }
//        return cnt;
//    }

    private long sum; //统计逆序对的个数
    private int[] nums;

    //归并排序
    public int reversePairs(int[] nums) {
        sum = 0;
        this.nums = nums;
        int l = 0;
        int r = nums.length - 1;
        divide(l, r);
        return (int) sum;
    }

    /**
     * 待分解的区间[l,r]
     *
     * @param l
     * @param r
     */
    private void divide(int l, int r) {
        System.out.println("divide(" + l + "," + r + ")");
        if (l == r)
            return;
        int mid = (l + r) >> 1; //取中间值
        divide(l, mid); //对左边[l,mid]进行递归
        divide(mid + 1, r); //对右边[mid+1,r]进行递归
        merge(l, r, mid);
    }

    private void merge(int l, int r, int mid) {
        System.out.println("merge(l:" + l + ",r:" + r + ",mid:" + mid + ")");
        System.out.print("当前nums数组为：");
        for (int n = 0; n < nums.length; n++)
            System.out.print(nums[n] + " ");
        System.out.println();
        System.out.print("当前左区间为：");
        for (int p = l; p <= mid; p++)
            System.out.print(nums[p] + ",");
        System.out.print("   ");
        System.out.print("当前右区间为：");
        for (int p = mid + 1; p <= r; p++)
            System.out.print(nums[p] + ",");
        System.out.println();
        int i = l;  //左区间的起点
        int j = mid + 1; //右区间的起点
        int[] tmp = new int[r - l + 1];  //排序的数组
        int index = 0;
//        System.out.println("l="+l);
//        System.out.println("r="+r);
        while (i <= mid && j <= r) {
            if (nums[i] > nums[j]) {
                System.out.println("前大于后，构成逆序，需要排序：" + nums[i] + ">" + nums[j]);
                System.out.println(nums[j] + " 已加到tmp数组");
                tmp[index++] = nums[j++];  //num[i]留到后面加到index数组
                sum += mid - i + 1; //此前，[l,mid]已经保证是有序（升序）的了，所以num[i]后面的都是大于num[j]的，都与num[j]构成逆序对
            } else {
                System.out.println("前小于后，不需要排序：" + nums[i] + "<" + nums[j]);
                System.out.println(nums[i] + " 已加到tmp数组");
                tmp[index++] = nums[i++];
            }
        }
        //上面while循环的两个条件可能有一个还满足
        while (i <= mid) {
            System.out.println(nums[i] + " 已加到tmp数组");
            tmp[index++] = nums[i++];
        }
        while (j <= r) {
            System.out.println(nums[j] + " 已加到tmp数组");
            tmp[index++] = nums[j++];
        }
        index = 0;
        System.out.print("[nums[l:" + l + "], nums[r:" + r + "]]左右区间合并后: ");
        for (int k = l; k <= r; k++) {
            nums[k] = tmp[index++];  //使用临时排序数组更新原数组
            System.out.print(nums[k] + " ");
        }
        System.out.println("\n------------------------");
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 2, 3, 1};
        new Solution().reversePairs(nums);
    }

}
