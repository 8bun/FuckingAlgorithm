package 前k个高频元素;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/4/15 18:34
 * @Description: 跟找前k个最大的数字思路一样，这里使用的最小堆存储的是前k个频率以及他们的数字
 * @Url: https://leetcode-cn.com/problems/top-k-frequent-elements/solution/20200406347medianhashmaplisttong-pai-xu-by-jasion_/
 * @限制:
 * @Level:
 */
public class Solution {

    public List<Integer> topKFrequent(int[] nums, int k) {

        HashMap<Integer,Integer> numsMap=new HashMap<>();
        for (int num:nums)
            numsMap.put(num,numsMap.getOrDefault(num,0)+1);

        //key：num，value：频率，堆按频率大小构成小根堆
        //比较o1、o2的"大小"来确定他们的位置，"大"的在前面
        //这里理解：优先队列是一个队列数据结构，下面比较器定义了元素存储的规则，（有两种选择来体现）先存储的元素是getValue值大的，也就是频率大的先
        PriorityQueue<Map.Entry<Integer,Integer>> priorityQueue=new PriorityQueue<>((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        for (Map.Entry<Integer,Integer> entry:numsMap.entrySet())
            priorityQueue.add(entry);
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<k;i++)
            list.add(priorityQueue.remove().getKey());
        return list;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
