package 美团;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author cwq
 * @since 2021/03/20
 */

public class Main4 {
    static HashMap<Integer, Pair<Integer, Integer>> step = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m0 = scanner.nextInt();
        int w0 = scanner.nextInt();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String ab = scanner.next();
            if("A".equals(ab)) {
                int weight = scanner.nextInt();
                int p = scanner.nextInt() / 100;
                step.put(i, new Pair<>(weight, p));
            }
            else if("B".equals(ab)) {
                
            }
        }
    }
}
