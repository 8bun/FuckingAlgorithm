package 粤港澳;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/4/25 14:36
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class M {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int level=1;
        HashMap<Integer,Integer> map=new HashMap<>();
        HashSet<Integer> set=new HashSet<>();
        int res=0;
        while (true){
            Integer num1=scanner.nextInt();
            if (num1==-1)
                break;
            Integer num2=scanner.nextInt();
            map.put(num1,map.getOrDefault(num1,0)+1);
            map.put(num2,map.getOrDefault(num2,0)+1);
            set.add(num1);
            set.add(num2);
            if (level==set.size()){
                if (map.getOrDefault(num1,0)==1) {
                    set.remove(num1);
                }
                if (map.getOrDefault(num2,0)==1) {
                    set.remove(num2);
                }
                else {
                    map.put(num1,map.getOrDefault(num1,0)-1);
                    map.put(num2,map.getOrDefault(num2,0)-1);
                }
                res++;
                continue;
            }
            level++;
        }
        System.out.println(res);
    }
}
