package 粤港澳;

import java.util.*;

/**
 * @Author: Jack
 * @Date: 2020/4/25 17:11
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class O {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int nums=scanner.nextInt();
            LinkedList<Integer> list=new LinkedList<>();
            for (int i=0;i<nums;i++)
                list.add(scanner.nextInt());
            Collections.sort(list);
            int res=0;
            while (list.size()>1){
                int first=list.removeFirst();
                int last=list.removeLast();
                res+=first*last;
                list.add(first+last);
                Collections.sort(list);
            }
            System.out.println(res);
        }
    }
}
