package 天梯;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/28 15:42
 * @level：
 */
public class Main6 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int k=scanner.nextInt();
        int n=scanner.nextInt();
        ArrayList<String> arrayList=new ArrayList<>();
        HashMap<String,String> map=new HashMap<>();
        map.put("Genji","Hanzo");
        map.put("Mccree","Genji");
        map.put("Hanzo","Mccree");
        String str="";
        while (!(str=scanner.next()).equals("End")){
            arrayList.add(str);
        }
        ArrayList<String> arrayList1=new ArrayList<>();
        for(int i=1;i<=arrayList.size();i++){
//            System.out.println(arrayList.get(i));
//            System.out.println();
            int m=i%k;
            int l=i%n;
            if(m==0){
                arrayList1.add(arrayList.get(i-1));
            }
            else if(l==0){
                arrayList1.add("Mercy");
                arrayList1.add("Heroes never die");
            }
            else {
                String s=arrayList.get(i-1);
                s=map.get(s);
                arrayList1.add(s);
            }
        }
        for (int i=0;i<arrayList1.size();i++){
            System.out.println(arrayList1.get(i));
        }
    }
}
