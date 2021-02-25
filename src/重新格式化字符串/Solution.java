package 重新格式化字符串;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/19 10:41
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class Solution {

    LinkedList<String> listnum=new LinkedList<>();
    LinkedList<String> listch=new LinkedList<>();
    public String reformat(String s) {
        for (char ch:s.toCharArray()){
            if (ch>='a'&&ch<='z')
                listch.add(ch+"");
            else if(ch>='0'&&ch<='9')
                listnum.add(ch+"");
        }
        int listch_size=listch.size();
        int listnum_size=listnum.size();
        if((listch_size==1&&listnum_size==0)||(listch_size==0&&listnum_size==1))
            return s;
        else if (listch_size==0||listnum_size==0){
            return "";
        }
        else{
           if(listch_size+1==listnum_size){
               String res="";
               while (listch.size()!=0){
                   res+=listnum.removeFirst();
                   res+=listch.removeFirst();
               }
               res+=listnum.removeFirst();
               return res;
           }
           else if(listch_size==listnum_size+1){
               String res="";
               while (listnum.size()!=0){
                   res+=listch.removeFirst();
                   res+=listnum.removeFirst();
               }
               res+=listch.removeFirst();
               return res;
           }
           else if (listch_size==listnum_size){
               String res="";
               while (listnum.size()!=0){
                   res+=listch.removeFirst();
                   res+=listnum.removeFirst();
               }
               return res;
           }
           else return "";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
