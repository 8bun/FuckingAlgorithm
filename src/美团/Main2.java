package 美团;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author cwq
 * @since 2021/03/20
 */

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        Arrays.sort(a);
        Arrays.sort(b);
        if(a[0] == b[0]) {
            System.out.println(0);
            return;
        }
        long res = b[0] + m - a[0];
        int i = 1;
        while (true) {
            if(((a[0] + res) % m == b[0]) && ((a[n - 1] + res) % m == b[n - 1])) {
                break;
            }
            i++;
            res = i * res;
        }
        System.out.println(res);
    }
}
