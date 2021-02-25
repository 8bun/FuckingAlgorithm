package Java基础.排序算法.冒泡排序;

import java.util.Arrays;

/**
 * @description：
 * 将序列中所有元素两两比较，将最大的放在最后面。
 * 将剩余序列中所有元素两两比较，将最大的放在最后面。
 * 重复第二步，直到只剩下一个数。
 * 伪代码：
 * 设置循环次数。
 * 设置开始比较的位数，和结束的位数。
 * 两两比较，将最小的放到前面去。
 * 重复2、3步，直到循环次数完毕。
 * 假设 [1,10,35,61,89,36,55]数组进行排序
 * 所以，经过第一趟的冒泡排序，可以确定，最大的数一定位于数组最后一位，第一趟排序编号假设为0，那么需要与下一位进行比较
 * 的元素下标范围为[0,len-1)也就是[0,len-2]
 * 经过第二趟的冒泡排序，可以确定，第二大的数一定位于数组倒数第二位，
 * 第二趟排序编号为1，那么需要与下一位进行比较
 * 的元素下标范围为[0,len-2)也就是[0,len-3]，也就是舍弃最后一个元素，之前的所有元素进行两两相邻比较
 * 总结：
 * N个数字要排序完成，总共进行N-1趟排序，每i趟的排序次数为(N-i)次，所以可以用双重循环语句，
 * 外层控制循环多少趟，内层控制每一趟的循环次数。（可以想象n=3的情况）
 * 冒泡排序总的平均时间复杂度为：O(n2)
 * @url： https://www.cnblogs.com/bigdata-stone/p/10464243.html
 * @限制：
 * @author：Jack
 * @createTime：2020/3/24 22:05
 * @level：
 */
public class Main {
    public static void main(String[] args) {
        int []arr = {9,13,7,1,68,4,3,2,1};
        bubbleSort(arr);
    }
    public static void bubbleSort(int[] a){
        int len=a.length;
        int temp;
        for(int i=0;i<len-1;i++) { //一趟排序的编号，总共需要len-1趟，可以想象只有2个元素的样子，只需要1趟
            for (int j = 0; j < len - 1 - i; j++) {  //该趟排序的需要与下一个元素进行比较的下标范围
                if(a[j]>a[j+1]){
                    temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
