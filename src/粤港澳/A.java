package 粤港澳;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/25 09:47
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class A {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String date=scanner.nextLine();
            String info = scanner.nextLine();
            int k;
            if (isValid(date,info)) {
                for (; ; ) {
                    k = 0;
                    for (char ch : date.toCharArray())
                        k += (ch - '0');
                    date=String.valueOf(k);
                    if (k < 10)
                        break;
                }
                StringBuilder newinfo = new StringBuilder("");
                int ch_a=0;
                int ch_A=0;
                int ch_space=0;
                boolean flag=true;
                for (char ch : info.toCharArray()) {
                    if (!(ch>='a'&&ch<='z'||ch>='A'&&ch<='Z'||ch==' ')){
                        flag=false;
                        break;
                    }
                    char newch;
                    if (isUpper(ch)) {
                        ch_A++;
                        newch = (char) (ch + k);
                        if (newch > 'Z')
                            newch = (char) ('A' + (k - ('Z' - ch) - 1));
                    } else if (ch == ' ') {
                        ch_space++;
                        newch = '#';
                    }
                    else {
                        ch_a++;
                        newch = (char) (ch + k);
                        if (newch > 'z')
                            newch = (char) ('a' + (k - ('z' - ch) - 1));
                    }
                    newinfo.append(newch);
                }
                if (ch_A==info.length()||ch_a==info.length()||ch_space==info.length())
                    flag=false;
                if (flag)
                    System.out.println(newinfo.toString());
                else System.out.println("none");
            }
            else System.out.println("none");
        }
    }
    private static boolean isUpper(char ch){
        return ch>='A'&&ch<='Z';
    }
    private static boolean isValid(String str,String info){
        if (str.isEmpty()||info.isEmpty())
            return false;
        else {
            if (str.length() == 8&&info.length()<=128) {
                // 闰年标志
                boolean isLeapYear = false;
                String year = str.substring(0, 4);
                String month = str.substring(4, 6);
                String day = str.substring(6, 8);
                int vYear = Integer.parseInt(year);
                // 判断年份是否合法
                if (vYear < 1900 || vYear > 2020) {
                    return false;
                }
                // 判断是否为闰年
                if (vYear % 4 == 0 && vYear % 100 != 0 || vYear % 400 == 0) {
                    isLeapYear = true;
                }
                // 判断月份
                // 1.判断月份
                if (month.startsWith("0")) {
                    String units4Month = month.substring(1, 2);
                    int vUnits4Month = Integer.parseInt(units4Month);
                    if (vUnits4Month == 0) {
                        return false;
                    }
                    if (vUnits4Month == 2) {
                        // 获取2月的天数
                        int vDays4February = Integer.parseInt(day);
                        if (isLeapYear) {
                            if (vDays4February > 29)
                                return false;
                        } else {
                            if (vDays4February > 28)
                                return false;
                        }
                    }
                } else {
                    // 2.判断非0打头的月份是否合法
                    int vMonth = Integer.parseInt(month);
                    if (vMonth != 10 && vMonth != 11 && vMonth != 12) {
                        return false;
                    }
                }
                // 判断日期
                // 1.判断日期
                if (day.startsWith("0")) {
                    String units4Day = day.substring(1, 2);
                    int vUnits4Day = Integer.parseInt(units4Day);
                    if (vUnits4Day == 0) {
                        return false;
                    }
                } else {
                    // 2.判断非0打头的日期是否合法
                    int vDay = Integer.parseInt(day);
                    if (vDay < 10 || vDay > 31) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }
}
