package 天梯;

import java.util.Scanner;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/28 16:10
 * @level：
 */
public class Main7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        long n = scanner.nextInt();
        int len = str.length();
        if (n <= 0||len==0) {
            System.out.println("0 0");

        } else {
            long x = 0;
            long y = 0;
            for (int i = 0; i < len; i++) {
                char ch = str.charAt(i);
                if (ch == 'W') {
                    y++;
                } else if (ch == 'S') {
                    y--;
                } else if (ch == 'A') {
                    x--;
                } else if (ch == 'D') {
                    x++;
                }
            }
            x*=(n/len);
            y*=(n/len);
            long tmp=n%len;
            for(int i=0;i<tmp;i++){
                char ch = str.charAt(i);
                if (ch == 'W') {
                    y++;
                } else if (ch == 'S') {
                    y--;
                } else if (ch == 'A') {
                    x--;
                } else if (ch == 'D') {
                    x++;
                }
            }
            System.out.println(x + " " + y);
        }
    }
}
