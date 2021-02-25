package 汽水瓶;

import java.util.Scanner;

/**
 * @description：
 * @url： https://www.nowcoder.com/questionTerminal/fe298c55694f4ed39e256170ff2c205f
 * @限制：
 * @author：Jack
 * @createTime：2020/4/5 9:56
 * @level：
 */
public class Main {
       public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            int left=scanner.nextInt();
            if(left>=1&&left<=100) {
                int drink = 0;
                while (left > 2) {
                    drink += left / 3;
                    left = left % 3 + left / 3;
                    if (left == 2) {
                        drink += 1;
                        break;
                    }
                }
                System.out.println(drink);
            }
        }
    }

}
