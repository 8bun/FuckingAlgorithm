package 粤港澳;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/25 11:58
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class C {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String str=scanner.nextLine();
            if (str.startsWith("#"))
                break;
            String[] st1=str.split("\\s+");
            boolean flag=true;
            HashMap<String, Integer> map=new HashMap<>();
            for (String st:st1){
                if (map.containsKey(st)){
                    map.put(st,map.get(st)+1);
                }
                else map.put(st,1);
                if (map.get(st)>st1.length/2) {
                    System.out.println("no");flag=false;break;
                }
            }
            if (flag)
                System.out.println("yes");
        }
    }
}
