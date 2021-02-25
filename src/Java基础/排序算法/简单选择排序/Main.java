package Java基础.排序算法.简单选择排序;

/**
 * @description：常用于取序列中最大最小的几个数时
 * ①. 从待排序序列中，找到关键字最小的元素；
 * ②. 如果最小元素不是待排序序列的第一个元素，将其和第一个元素互换；
 * ③. 从余下的 N - 1 个元素中，找出关键字最小的元素，重复①、②步，直到排序结束
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/24 22:05
 * @level：
 */
public class Main {
    public static void main(String[] args) {
        int a[]=new int[]{22,11,11,66,33,55,44};
        int len=a.length;
        for (int i=0;i<len;i++){
            int min=a[i];  //未排序数组的第一个初始化为min
            int pos=i;   //最小值的位置
            for(int j=i+1;j<len;j++){
                //找到未排序数组的最小值a[j]，并记录位置j
                if(a[j]<min){
                    min=a[j];
                    pos=j;
                }
            }
            //将最小值放在未排序数组的第一个位置，也就是与第一个元素进行交换
            a[pos]=a[i];
            a[i]=min;
        }
        // testPractice(a);
        for (int i=0;i<len;i++)
            System.out.print(a[i]+" ");
    }

    private static void testPractice(int[] a) {

        for(int i = 0; i < a.length; i++) {
            int min = a[i];
            int pos = i;
            for (int j = i + 1; j < a.length; j++) {
                if (min > a[j]) {
                    min = a[j];pos = j;
                }
            }
            if (pos != i) {
                a[pos] = a[i];
                a[i] = min;
            }
        }
        for (int i : a) System.out.print(i+" ");
    }

}
