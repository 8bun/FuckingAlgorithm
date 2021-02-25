package 丑数;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @description：丑数即一个数等于 2^x * 3^y * 5^z, 其中x、y、z可以为0，
 *
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/5 9:54
 * @level：
 */
public class Solution2 {

    public int nthUglyNumber(int n) {
        //避免乘后溢出
        PriorityQueue<Long> priorityQueue=new PriorityQueue<>();
        Set<Long> set=new HashSet<>();
        int cnt=1;
        long num=1;
        long[] help = new long[]{2, 3, 5};  //方便遍历
        priorityQueue.offer(num);
        set.add(num);
        //每次取出堆顶（最小的丑数，然后将其的2，3，5倍压入堆）
        //第一次取出1，压入2，3，5
        //第二次取出2，压入4，6，10，堆还剩3,4,5,6,10
        //第三次取出3，压入8，12，20，堆还剩5,6,8,10,12,20
        //第四次取出5, 压入10（之前已经压入）, 15, 25，堆还剩6,8,10,15,20
        //..
        //第n次取出目标值
        //我们发现有之前已经压入的值，如果再压入堆则会导致错误，所以使用Set来存储，虽然
        //优先队列也存储，但是其主要作用是返回最小值
        while (cnt<=n){
            num=priorityQueue.poll();
            for(int j=0;j<3;j++){
                if(!set.contains(num*help[j])){
                    set.add(num*help[j]);
                    priorityQueue.offer(num*help[j]);
                }
            }
            cnt++;  //cnt++后等于n时，取出了cnt-1个数，所以循环还要加上=n
        }
        return (int) num;
    }
}
