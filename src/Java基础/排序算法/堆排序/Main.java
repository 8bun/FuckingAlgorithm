package Java基础.排序算法.堆排序;

import java.util.Arrays;

/**
 * @description： 堆是具有以下性质的完全二叉树，每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；
 * 或者每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。
 * 同时，我们对堆中的结点按层进行编号，将这种逻辑结构映射到数组中，
 * 该数组从逻辑上讲就是一个堆结构，我们用简单的公式来描述一下堆的定义就是：
 * 大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
 * 小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
 * https://www.cnblogs.com/chengxiao/p/6129630.html
 * @url： https://mp.weixin.qq.com/s/FqpnIolRYPAM59ol1e-bMw
 * @限制： 堆排序中初始化堆的过程比较次数较多, 因此它不太适用于小序列
 * 同时由于多次任意下标相互交换位置, 相同元素之间原本相对的顺序被破坏了, 因此, 它是不稳定的排序。
 * ①. 建立堆的过程, 从length/2 一直处理到0, 时间复杂度为O(n);
 * ②. 调整堆的过程是沿着堆的父子节点进行调整, 执行次数为堆的深度, 时间复杂度为O(logn);
 * ③. 堆排序的过程由n次第②步完成, 时间复杂度为O(nlogn).
 * @author： Jack
 * @createTime：2020/3/24 22:05
 * @level：
 */

/**
 * 为什么升序要用大顶堆呢
 * 大顶堆的特点：每个结点的值都大于或等于其左右孩子结点的值，我们把大顶堆构建完毕后根节点的值一定是最大的，
 * 然后把根节点和最后一个元素（也可以说最后一个节点）交换位置，那么末尾元素此时就是最大元素了
 * (因为堆排序的思想是通过交换堆顶元素和每次重建的堆的最后一个元素，也就是如果要使得升序，那么越大的要
 * 越往后去挪，也就是越大的应该在堆顶被交换，所以要使用大顶堆)
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        heapSort(arr);
    }

    public static void heapSort(int[] a) {

        System.out.println("开始排序");
        int arrayLength = a.length;
        //循环建堆
        for (int i = 0; i < arrayLength - 1; i++) {
            //建堆,全局建堆，交换完第一个和最后一个之后，下一次建堆，舍弃最后一个元素
            buildMaxHeap(a, arrayLength - 1 - i);
            //交换堆顶和最后一个元素
            swap(a, 0, arrayLength - 1 - i);
            System.out.println(Arrays.toString(a));
        }
    }

    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    //对data数组从0到lastIndex建大顶堆
    private static void buildMaxHeap(int[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //k保存正在判断的节点
            int k = i;
            //如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                //k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex小于lastIndex，那么就表示biggerIndex还不是最后一个数组元素，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    //若果右子节点的值较大
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    //交换他们
                    swap(data, k, biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }
}
