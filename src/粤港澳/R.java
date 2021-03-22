package 粤港澳;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author cwq
 * @since 2020/4/25 15:34
 * @Description:
 * @link
 * @限制:
 * @Level:
 */
public class R {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nums = scanner.nextInt();
        for (int i = 0; i < nums; i++) {
            int len = scanner.nextInt();
            if (len <= 2) {
                System.out.println(len);
                continue;
            }
            System.out.println(BigDecimal.valueOf((float) Math.pow(2, len) - ((len - 1) * 2 + 1)));
        }
    }
}
