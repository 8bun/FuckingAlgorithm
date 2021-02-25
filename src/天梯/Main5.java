package 天梯;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/28 14:59
 * @level：
 */
public class Main5 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        ArrayList<ArrayList<String>> sits=new ArrayList<>();
        int k=1;
        int res_k=1;
        boolean flag=false;
        for(int i=0;i<n;i++){
            ArrayList<String> arrayList=new ArrayList<>();
            String str=scanner.next();
            if(str.charAt(0)=='-')
                k++;
            if(!flag && str.charAt(0)==str.charAt(1)&&str.charAt(1)=='O'){
                str="++"+str.substring(2);
                res_k=k;
                flag=true;
            }
            else if(!flag && str.charAt(3)==str.charAt(4)&&str.charAt(3)=='O'){
                str=str.substring(0,3)+"++";
                res_k=k;
                flag=true;
            }
            arrayList.add(str);
            sits.add(arrayList);
        }
        if(flag) {
            System.out.println("Yes");
            System.out.println(res_k);
            for(int i=0;i<n;i++){
                System.out.println(sits.get(i).toString().substring(1,6));
            }
        }
        else System.out.println("No");

    }
}
