package 粤港澳;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/25 12:46
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class D {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num=scanner.nextInt();
        ArrayList<ArrayList<Integer>> res=new ArrayList<>();
        for (int i=0;i<3;i++){
            ArrayList<Integer> arrayList=new ArrayList<>();
            res.add(arrayList);
        }
        int first[]=new int[num];
        for (int i=0;i<num;i++)
            first[i]=scanner.nextInt();
        for (int i=0,say=1;i<num;i++,say++){
            if (say==4)
                say=1;
            res.get(say-1).add(first[i]);
        }
        for (ArrayList<Integer> list:res){
            for (Integer integer:list){
                System.out.print(integer+" ");
            }
            System.out.println();
        }
    }
}
