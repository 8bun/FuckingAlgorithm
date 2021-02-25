package 天梯;

import java.util.*;

/**
 * @description：
 * 用两个队列来进行遍历输出
 * 一个队列first保存每个朋友圈第一个加入排队的元素
 * 另一个队列detail保存每个朋友圈的进入排队的所有元素
 * 每次输出时，遍历detail及其相应的first即可，first队列是否需要出队看detail是否为空
 * 待优化
 * @url：
 * @限制： Java超时
 * @author：Jack
 * @createTime：2020/3/29 10:38
 * @level：
 */
public class Main10 {
    public static void main(String[] args) {
        int n;
        int m;
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt();
        m=scanner.nextInt();

        HashMap<String,Integer> map=new HashMap<>();
        for(int i=0,id=0;i<n;i++,id++){
            int p_num=scanner.nextInt();
            String nums[]=new String[p_num];
            for(int j=0;j<p_num;j++){
                nums[j]=scanner.next();
                map.put(nums[j],id);
            }
        }
        LinkedList<String> first=new LinkedList<>();
        HashMap<Integer,Queue<String>> details=new HashMap<>();
        for(int i=0;i<m;i++) {
            String in_or_out = scanner.next();
            if (in_or_out.equals("IN")) {
                String numIn=scanner.next();
                int id=map.get(numIn);
                if(!details.containsKey(id)){
                    LinkedList<String> q=new LinkedList<>();
                    q.add(numIn);
                    details.put(id,q);
                    first.add(numIn);
                }else {
                    details.get(id).add(numIn);
                }
            }
            else if(in_or_out.equals("OUT")){
                if (first.isEmpty()){
                    System.out.println("EMPTY");
                    continue;
                }
                String outFirst=first.peek();
                int id=map.get(outFirst);
                if(details.containsKey(id)){
                    System.out.println(details.get(id).poll());
                    if(details.get(id).isEmpty()) {
                        first.poll();
                        details.remove(id);
                    }
                }
                else {
                    System.out.println("EMPTY");
                }
            }
        }
    }
}
