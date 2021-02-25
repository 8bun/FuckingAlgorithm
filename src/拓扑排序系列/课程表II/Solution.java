package 拓扑排序系列.课程表II;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/7/9 17:26
 * @Description: 根据图，图的元素个数为n,如果是拓扑图，则输出0->n-1号的任意一种拓扑路径，否则输出[]
 * @Url: https://leetcode-cn.com/problems/course-schedule-ii/
 * @限制:
 * @Level:
 */
public class Solution {

    //BFS
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[]{};
        int[] inDegrees = new int[numCourses]; //入度数组,inDegrees[i]=j表示编号i的课程的入度数为j
        for (int[] p : prerequisites) inDegrees[p[0]]++;

        Deque<Integer> deque = new ArrayDeque<>();
        //遍历一次，先将所有入度为0的课程加入队列
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) deque.offer(i);
        }
        int cnt = 0; //记录已经修好的课程，表示可以从图中删除以及其关联的边,则会影响到后继的入度-1
        int[] res = new int[numCourses];

        //只有入度为0的课程才能进入队列
        while (! deque.isEmpty()) {
            int cur = deque.poll();
            res[cnt++] = cur;
            for (int[] p : prerequisites) {
                if (p[1] == cur){
                    inDegrees[p[0]]--;
                    if (inDegrees[p[0]] == 0) deque.offer(p[0]);
                }
            }
        }
        return cnt == numCourses ? res : new int[]{};
    }

    //DFS



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
