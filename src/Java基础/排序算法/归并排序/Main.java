package Java基础.排序算法.归并排序;

import java.util.Arrays;

/**
 * @description： 速度仅次于快排，内存少的时候使用，可以进行并行计算的时候使用
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/24 22:05
 * @level：
 */
public class Main {
    public static void main(String[] args) {
        int []arr = {9,8,7,6,5,4,3,2,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int[] arr){
        int[] tmp=new int[arr.length];  //存放结果,tmp始终是有序的
        sort(arr,0,arr.length-1,tmp);
    }

    private static void sort(int[] arr, int left, int right, int[] tmp) {
        if(left<right){
            int mid=(left+right)/2;
            sort(arr,left,mid,tmp);  //对左边进行归并排序，使得左子序列有序
            sort(arr,mid+1,right,tmp);//右边归并排序，使得右子序列有序
            merge(arr,left,mid,right,tmp);//将两个有序子数组合并操作
        }
    }
    private static void merge(int[] arr,int left,int mid,int right,int[] tmp) {
        //此时[left,mid]和[mid+1,right]都是有序的了
        int i=left;  //左序列指针
        int j=mid+1;  //右序列指针
        int t=0; //临时指针
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                tmp[t++]=arr[i++];
            }else {
                tmp[t++]=arr[j++];
            }
        }
        while (i<=mid){ //将左边剩余元素填充进tmp中
            tmp[t++]=arr[i++];
        }
        while (j<=right){//将右序列剩余元素填充进tmp中
            tmp[t++]=arr[j++];
        }
        t=0;
//        for (int m=0;m<arr.length; m++)
//            System.out.print(arr[m]+" ");
//        System.out.println();
        //最后，将tmp数组复制给arr数组
        while (left<=right){
            arr[left++]=tmp[t++];
        }
    }

}
