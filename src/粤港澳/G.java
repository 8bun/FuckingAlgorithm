package 粤港澳;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/25 16:32
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class G {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nums=scanner.nextInt();
        int[] arr=new int[nums];
        for (int i=0;i<nums;i++){
            arr[i]=scanner.nextInt();
        }
        Arrays.sort(arr);
        System.out.println(arr[nums/2]);
    }
}
