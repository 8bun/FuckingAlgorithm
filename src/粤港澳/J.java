package 粤港澳;

import java.util.Scanner;

/**
 * @Author: Jack
 * @Date: 2020/4/25 13:18
 * @Description:
 * @Url:
 * @限制:
 * @Level:
 */
public class J {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nums=scanner.nextInt();
        for (int i=0;i<nums;i++){
            String bit_0=scanner.next();
            int[] pows={128,64,32,1,-1,16,2,4,8};

            int[] res=new int[4];
            for (int index=0,p=bit_0.charAt(4)-48;index<9;index++){
                int num=bit_0.charAt(index)-48;
                if (index==4||(num^p)==0)
                    continue;
                res[0]+=(num^p)*pows[index];
            }
            if (res[0]==0){
                System.out.println(0);
                continue;
            }
            StringBuilder bit_90= new StringBuilder();
            int[] index_90={6,3,0,7,4,1,8,5,2};
            for (int k=0;k<9;k++)
                bit_90.append(bit_0.charAt(index_90[k]));
            for (int index=0,p=bit_90.charAt(4)-48;index<9;index++){
                int num=bit_90.charAt(index)-48;
                if (index==4||(num^p)==0)
                    continue;
                res[1]+=(num^p)*pows[index];
            }
            if (res[1]==0){
                System.out.println(0);
                continue;
            }
            StringBuilder bit_180= new StringBuilder();
            int[] index_180={8,7,6,5,4,3,2,1,0};
            for (int k=0;k<9;k++)
                bit_180.append(bit_0.charAt(index_180[k]));
            for (int index=0,p=bit_180.charAt(4)-48;index<9;index++){
                int num=bit_180.charAt(index)-48;
                if (index==4||(num^p)==0)
                    continue;
                res[2]+=(num^p)*pows[index];
            }
            if (res[2]==0){
                System.out.println(0);
                continue;
            }
            StringBuilder bit_270= new StringBuilder();
            int[] index_270={2,5,8,1,4,7,0,3,6};
            for (int k=0;k<9;k++)
                bit_270.append(bit_0.charAt(index_270[k]));

            for (int index=0,p=bit_270.charAt(4)-48;index<9;index++){
                int num=bit_270.charAt(index)-48;
                if (index==4||(num^p)==0)
                    continue;
                res[3]+=(num^p)*pows[index];
            }
            if (res[3]==0){
                System.out.println(0);
                continue;
            }
            int min=res[0];
            for (int j=1;j<4;j++){
                min=Math.min(res[j],min);
            }
            System.out.println(min);
        }
    }
}
