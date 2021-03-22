package 在既定时间做作业的学生人数;

import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/5/17 10:31
 * @Description:
 * @link
 * @限制:
 * @Level:
 */
public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int cnt = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime && endTime[i] <= queryTime) cnt++;
        }
        return cnt;
    }
}
