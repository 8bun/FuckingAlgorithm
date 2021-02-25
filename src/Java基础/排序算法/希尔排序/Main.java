package Java基础.排序算法.希尔排序;

/**
 * @description：
 * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，
 * 待整个序列中的记录“基本有序”时，再对全体记录进行依次直接插入排序
 * 将数的个数设为n，取半数k=n/2，将下标差值为k的数分为一组，构成有序序列。
 * 再取k=k/2 ，将下标差值为k的书分为一组，构成有序序列。
 * 重复第二步，直到k=1执行简单插入排序。
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/24 22:05
 * @level：
 */
public class Main {
    public static void main(String[] args) {
        int a[]=new int[]{22,11,11,66,33,55,44};
        int d=a.length;
        while (d!=0){ //d==1时，还要对下标间隔为1的元素进行排序
            d/=2;  //将两两间隔为d的元素分为一组，d/2刚好就是组数
            for(int x=0;x<d;x++){
                for (int i=x+d;i<a.length;i+=d) //x+d为第d组的第二个元素，从它开始，进行直接插入排序
                {
                    int j=i-d;  //有序序列最后一个元素
                    int temp=a[i];  //待插入的元素
                    while (j>=0&&a[j]>temp){
                        a[j+d]=a[j]; //向右移动d位
                        j-=d;
                    }
                    a[j+d]=temp;
                }
            }
        }
        for (int i=0;i<a.length;i++)
            System.out.print(a[i]+" ");
    }
}
