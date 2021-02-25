package Java基础.排序算法.直接插入排序;

/**
 * @description： 将把新的数据插入到已经排好的数据列中
 * 首先设定插入次数，即循环次数，for(int i=1;i<length;i++)，1个数的那次不用插入。
 * 设定插入数和得到已经排好序列的最后一个数的位数。insertNum和j=i-1。
 * 从最后一个数开始向前循环，如果插入数小于当前数，就将当前数向后移动一位。
 * 将当前数放置到空着的位置，即j+1。
 * @url：
 * @限制： 当数据量太大时，采用希尔排序
 * @author：Jack
 * @createTime：2020/3/24 22:05
 * @level：
 */
public class Main {
    public static void main(String[] args) {
        int a[]=new int[]{22,11,11,66,33,55,44};
        int len=a.length;
        testPractice(a);
        int next=1;  //下一个要加入排好序组合的元素下标
        int before_last_next;   //前面已经排好序的最后一个元素下标
        for (;next<len;next++){
            int insertNum=a[next];  //要插入的数，先保存下来，以免移位将其覆盖
            before_last_next=next-1;
            //从已排好序的数组从后往前遍历，发现比插入的数大的，就右移
            while (before_last_next>=0&&a[before_last_next]>insertNum){
                a[before_last_next+1]=a[before_last_next]; //右移一位
                before_last_next--;
            }
            a[before_last_next+1]=insertNum;
        }
        for (int i=0;i<len;i++)
            System.out.print(a[i]+" ");
    }

    private static void testPractice(int[] a) {

        for (int i = 1; i < a.length; i++) {
            int pos = i - 1;
            int insert = a[i];
            while(pos >= 0 && a[pos] >= insert) {
                a[pos + 1] = a[pos];
                pos--;
            }
            a[pos + 1] = insert;
        }
        for (int i=0; i<a.length;i++)
            System.out.print(a[i]+" ");
    }
}
