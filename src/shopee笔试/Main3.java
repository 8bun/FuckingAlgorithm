package shopee笔试;


import java.util.Arrays;
import java.util.Scanner;

/**
 * @author cwq
 * @since 2021/03/17
 */

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strs = str.split(",");
        for (int i = 0; i < strs.length; i++) {
            strs[i] = strs[i].trim();
            strs[i] = strs[i].substring(1, strs[i].length() - 1);
        }
        for(int i = 0; i < strs.length; i++) {
            int dot = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                char c = strs[i].charAt(j);
                if(c == '.') {
                    if (dot == 1) {
                        strs[i] = strs[i].substring(0, j);break;
                    }
                    dot++;
                }
            }
        }
        if(strs[0].compareTo(strs[1]) > 0) {
            System.out.println(1);
        }
        else if(strs[0].compareTo(strs[1]) < 0) {
            System.out.println(-1);
        }
        else {
            System.out.println(0);
        }
    }
}
