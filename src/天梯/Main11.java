package 天梯;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/29 19:47
 * @level：
 */
public class Main11 {

    private static HashMap<Integer,Stack<Integer>> num_sons=new HashMap<>();
    private static int depth=0;
    private static int[] cnt=new int[100005];  //num[i]为层号为i的总人数
    public static void main(String[] args) {
        int num;
        Scanner scanner=new Scanner(System.in);
        num=scanner.nextInt();
        float[] salary=new float[num+1]; //salary[i]为编号为i的工资
        float[] sumSalary=new float[num+1]; //sumSalary[i]为层号为i的总工资
        for(int i=1;i<=num;i++){
            int son_nums=scanner.nextInt();
            Stack<Integer> sons=new Stack<>();
            for(int j=1;j<=son_nums;j++){
                int tmp=scanner.nextInt();
                sons.add(tmp);
                num_sons.put(i,sons);
            }
            salary[i]=scanner.nextInt();
        }
        bfs(1,1,salary,sumSalary);
        System.out.println(depth);
        for(int i=1;i<=depth;i++){
            System.out.println("Level "+i+": "+String.format("%.2f",sumSalary[i]/cnt[i]));
        }
    }

    private static void bfs(int num,int level,float[] salary,float[] sumSalary){
        sumSalary[level]+=salary[num];
        cnt[level]++;
        if(!num_sons.isEmpty()) {
            if (num_sons.containsKey(num)) {
                Stack<Integer> sons = num_sons.get(num);
                while (!sons.isEmpty()) {
                    bfs(sons.pop(), level + 1, salary, sumSalary);
                }
            } else {
                depth = Math.max(depth, level);
            }
        }
    }
}
