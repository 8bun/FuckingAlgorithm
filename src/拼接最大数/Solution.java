package 拼接最大数;

/**
 * @Description:
 * @Url: https://leetcode-cn.com/problems/create-maximum-number/
 * 思路https://blog.csdn.net/qq_26410101/article/details/81746569
 * @限制:
 * @Author: Jack
 * @createTime: 2020/4/13 9:53
 * @level: 困难
 */
public class Solution {

    /**
     * 假设数组一为[3,4,6,5]、数组二为[9,1,2,5,8,3]、k = 5;
     * 组合情况有0 + 5、1 + 4、2 + 3、3 + 2、4 + 1五种情况,就是从此五种情况取出组合最大的一种;
     * 表示若数组二的元素个数>=k,则数组一的元素个数可以从0开始取,否则在数组二的大小基础上补.
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int len1=nums1.length,len2=nums2.length;
        int[] res=new int[k];
        for (int arr1_num_select=Math.max(0,k-len2);arr1_num_select<=len1 && arr1_num_select<=k;arr1_num_select++){
            int[] arr=mergeMaxArr(maxArr(nums1,arr1_num_select),maxArr(nums2,k-arr1_num_select),k);
            if(compare(arr,res,k))
                res=arr;
        }
        return res;
    }
    /**
     * 从num数组中找pos个数（按相对顺序）组建成新数组，使该数组构成的数最大
     * 维护一个单调递减栈：栈大小为目标个数的长度
     * 记录栈中的元素、外面还未遍历的元素的个数，来决定是否按单调递减栈的规则让小的出栈，大的进栈
     * 这里要注意，小于当前待入栈的栈中元素的个数要记录，因为有可能把全部出栈完了再入栈，而后面剩余还没被的元素再
     * 也填补不满栈，所以要出栈的个数受剩余未入栈的个数的限制，出栈到外面还未遍历的元素个数刚好能补充栈中空缺的位，
     * 后面的元素直接按顺序进栈
     * 为了方便记录栈中比当前要入栈的元素小的元素个数，使用数组来方便遍历，因为栈只能访问栈顶元素，要访问栈顶下的元素必须
     * 要出栈，比较麻烦。
     * @param num
     * @param pos
     * @return
     */
    private int[] maxArr(int[] num,int pos){
        int max_len=num.length;
        if (max_len==pos)
            return num;
        int[] res=new int[pos];
        int res_top_index=-1; //模拟栈顶指针，指向下一个res待填补的位置,res_top_index+1表示栈中现在已有的元素个数
        //max_len-cur_to_In得到当前还未进行遍历出栈入栈的元素个数：例如
        //num[]={1,2,8,9,10,2}，cur_to_In=2，那么6-2=4，表示包括当前正在访问的元素8，还未进行出栈入栈的元素个数
        //那么这些元素一定要能填满栈，就要保证res_top_index+1+max_len-cur_to_In>k，此时可以利用
        // 单调递减栈的规则来进出栈，直到该式=k就一直加入栈即可，所以可以利用这个来设计一个while循环
        //但是如果此时栈是空的，也就是res_top_index=-1，那也是直接进栈即可
        for (int cur_to_In=0;cur_to_In<max_len;cur_to_In++){
            //一个元素进栈前，先运用规则让比他小的元素出栈，直到满足一定的关系
            while (res_top_index+1>0 && num[cur_to_In]>res[res_top_index] && res_top_index+1+max_len-cur_to_In>pos)
                res_top_index--;  //回退表示出栈
            if(res_top_index<pos-1) //元素进栈，栈还没满，塞入下标为res_top_index+1的位置
                res[++res_top_index]=num[cur_to_In];
        }
        return res;
    }

    /**
     * 假设数组一最大组合为[6,5],数组二最大组合为[9,8,3],（数组不一定是递减的，也就是栈不一定是单调减的，后面的几个元素是直接加入的）
     * 进行双指针排序,排序后为[9,8,6,5,3]
     * pos=len1+len2=5
     * 与归并类似，但是这个排序后的结果不改变元素在原数组的相对顺序，
     * 使用双指针遍历两个数组，指针指向的元素大的填到新数组，然后右移动，继续比较
     * 这里要注意一个数组先遍历到最后的时候，
     * int[] num={2,5,6,4,4,0};
     * int[] num2={7,3,8,0,6,5,7,6,2};
     * 此时p1走到了最后一个指向0的地方，p2也指向0，那么下一位应该先取p2的0，因为0657620>0065762
     * 为什么选择p2指向的0先呢？我们发现是因为p2的下一位6大于了p1指向的0，所以p2优先级更高，也就是此时p1==len1-1，p2=某个值
     * p1指向数组短的那方
     * int[] num={2,5,6,4,4,3};
     * int[] num2={7,3,8,3,2,5,7,6,2};
     * @param maxArr2
     * @param maxArr1
     * @return int[]
     */
    private int[] mergeMaxArr(int[] maxArr1,int[] maxArr2,int pos){
        int p1=0,p2=0;
        int[] res=new int[pos];
        int index=0;
        while (index<pos){
            if (compare(maxArr1,maxArr2,p1,p2))
                res[index++]=maxArr1[p1++];
            else res[index++]=maxArr2[p2++];
        }
        return res;
    }

    /**
     * 获取结果组合中最大的
     * 例如
     * 6,7,6,0,4
     * 6,5,1,3,4
     * @param arr1
     * @param arr2
     * @return
     */
    private boolean compare(int[] arr1,int[] arr2,int k){
        //也是双指针进行比较即可，对位遍历
        int p=0;
        while (p<k&&arr1[p]==arr2[p])
            p++;
        return p==k||arr1[p]>=arr2[p];
    }

    /**
     * 如果遍历的过程中遇到相等的情况，我们就需要一直往后比，直到它们不相等分出大小为止
     * 例：
     * 6,7,1
     * 6,6,6,5,1,3,4
     * @param arr1
     * @param arr2
     * @param p1
     * @param p2
     * @return
     */
    private boolean compare(int[] arr1,int[] arr2,int p1,int p2){
        int len1=arr1.length,len2=arr2.length;
        while (p1<len1&&p2<len2&&arr1[p1]==arr2[p2]){
            p1++;p2++;
        }
        //此时p1,p2是否走过末尾，
        // 假设p2走过了末尾，那么也就是如
        //811113和811的情况，最大合并是8(1111)3(11)，先拿前面的1，先走过末尾的后面拿
        //p1，p2都没走过末尾，有下面两种情况，[]内的数字为此时p1和p2走到的数字
        //81[3]和711[8]0，最大合并是87(118)(13)(0),先拿后面的1，因为3<8
        //81[3]和711[2]，最大合并是87(1)(3)(11)(0)，先拿前面的1，因为3>2
        //也就是大的先拿
        return p2==len2||(p1<len1&&arr1[p1]>arr2[p2]);
    }

    public static void main(String[] args) {
        int[] num={3,8,5,3,4};
        int[] num2={8,7,3,6,8};
        int[] s=new Solution().maxNumber(num,num2,5);
        for (int a:s)
            System.out.print(a+" ");
        System.out.println();
    }
}
