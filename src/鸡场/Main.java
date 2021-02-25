package 鸡场;

import java.util.*;

/**
 * @description：
 * 小强有 n 个养鸡场，第 i 个养鸡场初始有 a[i] 只小鸡。与其他养鸡场不同的是，
 * 他的养鸡场每天增加 k 只小鸡，小强每天结束都会在数量最多的养鸡场里卖掉一半的小鸡，
 * 假如一个养鸡场有 x 只鸡，则卖出后只剩下 x/2 (向下取整)只鸡。问 m 天后小强的 n 个养鸡场一共多少只小鸡？
 *
 * 输入 第一行输入三个int 类型 n,m,k（1 <= n,m,k <= 10^6）
 * 第二行输入 n 个正整数，表示 n 个养鸡场初始鸡的个数
 * 输出 输出一个整数表示鸡的总数
 * 示例输入：
 * 3 3 100
 * 100 200 400
 * 输出：
 * 925
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/4/8 9:35
 * @level：
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int num=scanner.nextInt();
        int day=scanner.nextInt();
        int add=scanner.nextInt();
        int[] a=new int[num];
        Queue<Integer> nums=new PriorityQueue<>((o1, o2)->(o2-o1));
        int sum=0;
        int max_num;
        for(int i=0;i<num;i++){
            a[i]=scanner.nextInt();
            nums.add(a[i]);
            sum+=a[i];
        }
        int subNum;
        int addAllFromFirstDay=0;  //从第一天开始每个鸡场增加了多少个add

        for (int i=0;i<a.length;i++)
            System.out.println(a[i]);
        for (int i=1;i<=day;i++){

        }
//        for (int i=1;i<=day;i++){
//            addAllFromFirstDay=i*day;
//            max_num=nums.poll();
//            sum-=max_num;
//            subNum=(int)Math.floor((max_num+addAllFromFirstDay)>>1);
//            nums.add(max_num+addAllFromFirstDay-subNum);
//            sum+=max_num+addAllFromFirstDay-subNum;
//        }
    }
}
