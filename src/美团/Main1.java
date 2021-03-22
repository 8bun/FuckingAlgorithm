package 美团;

import java.util.Scanner;

/**
 * @author cwq
 * @since 2021/03/20
 */

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        String a = scanner.next();
        char[] chars = s.toCharArray();
        int len = a.length();
        int count = 0;
        while (! "".equals(a)) {
            for (char aChar : chars) {
                if (aChar == a.charAt(0)) {
                    a =  a.substring(1);
                    if ("".equals(a)) {
                        break;
                    }
                }
                count++;
            }
        }
        System.out.println(count - len + 1);
    }
}
