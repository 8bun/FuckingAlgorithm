package 粤港澳;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/25 11:04
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class B {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String year=scanner.next();
            String month=scanner.next();
            String day=scanner.next();
            count(year,month,day);
        }
    }

    private static void count(String y,String m,String d) {
        // TODO Auto-generated method stub
        int allDay = 0; 	//	用来叠加总天数
        long totalDay = 0;	//	总天数
        long week = 0;	//	第几周
        int mday = 0;	//	每个月对应的天数
        long weekDay = 0;
        int year = Integer.parseInt(y);
        int month = Integer.parseInt(m);
        int day = Integer.parseInt(d);

        totalDay = CalculateAllDay(year,month,day,allDay,mday);
        week = CalculateWeek(totalDay,week);
        weekDay = CalculateWeekDay(year,month,day)+3;
        System.out.println(week +" " + weekDay);
    }

    //	总天数
    private static long CalculateAllDay(int year, int month, int day, int allDay, int mday) {
        // TODO Auto-generated method stub
        for(int i=1;i<month;i++) {
            switch(i) {
                case 1:case 3:case 5:case 7:case 8:case 10:case 12:{
                    mday = 31;
                    break;
                }
                case 4:case 6:case 9:case 11:{
                    mday = 30;
                    break;
                }
                case 2:{
                    if( (year%4==0) && (year%100!=0 || year%400==0)) {
                        mday = 29;
                    }else {
                        mday = 28;
                    }
                    break;
                }
                default:
                    break;
            }
            allDay+=mday;
        }
        int year_366=(year-2000)/4;
        long res=year_366*366+(year-2000-year_366)*365;
        return allDay+day+res;
    }

    //	总周数
    private static long CalculateWeek(long totalDay,long week) {
        // TODO Auto-generated method stub
        if(totalDay%4==0) {
            week = totalDay/4;
        }else {
            week = totalDay/4+1;
        }
        return week;
    }

    private static long CalculateWeekDay(int y, int m, int d) {
        // TODO Auto-generated method stub
        if(m==1 || m==2) {
            m+=12;
            y--;
        }
        long iWeek = (d+2*m+3*(m+1)/5+y+ y/4-y/100+y/400)%7;
        return iWeek;
    }
}
