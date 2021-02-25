package 合并区间;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/4/15 17:59
 * @Description:
 * 数轴合并闭区间
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * @Url: https://leetcode-cn.com/problems/merge-intervals/
 * @限制:
 * @Level:
 */
public class Solution {

    public int[][] merge(int[][] intervals) {
        //java8新增的并行排序算法，基于fork/join框架
        Arrays.parallelSort(intervals, Comparator.comparingInt(x->x[0]));  //对每个intervals[]进行一维比较
        LinkedList<int[]> list=new LinkedList<>();
        for (int i=0;i<intervals.length;i++){ //对每一个一维数组进行处理
            if(list.size()==0||list.getLast()[1]<intervals[i][0])  //如果集合为空或者前一个的结束小于后一个的开始,如[0,1],[2,3]，不能合并
                list.add(intervals[i]);
            else //可以合并，更新前一个的结束，如[0,2] [0,1]
                list.getLast()[1]=Math.max(list.getLast()[1],intervals[i][1]);
        }
        int[][] res=new int[list.size()][2];
        int index=0;
        while (!list.isEmpty()){
            res[index++]=list.removeFirst();
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
