package Java基础.排序算法.基数排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description： 用于大量数，很长的数进行排序时。又称桶排序
 * @url： https://www.jianshu.com/p/68b78324628f
 * https://mp.weixin.qq.com/s/FqpnIolRYPAM59ol1e-bMw
 * 首先将所有待比较树脂统一为统一位数长度，接着从最低位开始，依次进行排序。
 * 1. 按照个位数进行排序。定义一个一维长度为10的二维数组arr[][]进行存储
 * 2. 按照十位数进行排序。
 * 3. 按照百位数进行排序。
 * 4. ...
 * @限制：
 * @author：Jack
 * @createTime：2020/3/24 22:05
 * @level：
 */

public class Main {
    public static void main(String[] args) {
        int arr[] = {324, 1234, 45, 87, 4};
        Basesort(arr, arr.length);
    }

    /**
     * 筛选出最大元素确定位数,从而确定需要分配收集的趟数
     *
     * @param array
     * @param n
     *
     * @return
     */
    public static int getTime(int array[], int n) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int time = 0;
        //判断位数;
        while (max > 0) {
            max /= 10;
            time++;
        }
        return time;
    }

    private static void Basesort(int[] array, int len) {
        //求最大位数
        int time = getTime(array, len);
        //十个队列，各自独立，分别存储数位数值为0-9的元素（先对应个位，然后十位，再百位...）
        List<ArrayList> queue = new ArrayList<ArrayList>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> queue1 = new ArrayList<Integer>();
            queue.add(queue1);
        }
        //进行time次分配和收集;
        for (int i = 0; i < time; i++) {
            //遍历数组元素，将相应位的数分配到队列相应位置中
            for (int j = 0; j < array.length; j++) {
                //得到数字的第time+1位数x，第0次就是个位，第1次就十位...，如25，个位是25%(10^1/10^0)
                int x = array[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                //更新队列的x处
                ArrayList<Integer> queue2 = queue.get(x);
                queue2.add(array[j]);
                queue.set(x, queue2);
            }
            int count = 0;//元素计数器;
            //收集队列元素;
            for (int k = 0; k < 10; k++) {
                while (queue.get(k).size() > 0) {
                    ArrayList<Integer> queue3 = queue.get(k);
                    //遍历队列，更新数组
                    array[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
