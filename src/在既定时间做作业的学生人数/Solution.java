package 在既定时间做作业的学生人数;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/17 10:31
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution {

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int cnt =0 ;
        for (int i = 0; i < startTime.length; i++){
            if (startTime[i] <= queryTime && endTime[i]<=queryTime)cnt++;
        }
        return cnt;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
