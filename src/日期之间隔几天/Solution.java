package 日期之间隔几天;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/5/23 17:37
 * @Description:
 * @Url: https://leetcode-cn.com/contest/weekly-contest-177/problems/number-of-days-between-two-dates/
 * @限制:
 * @Level:
 */
public class Solution {

    public int daysBetweenDates(String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date _date1 = null;
        try {
            _date1 = format.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date _date2 = null;
        try {
            _date2 = format.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  Math.abs((int) ((_date1.getTime() - _date2.getTime()) / (1000*3600*24)));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
