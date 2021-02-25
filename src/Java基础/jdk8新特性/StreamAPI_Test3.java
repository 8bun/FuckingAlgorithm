package Java基础.jdk8新特性;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @description：Stream的串行和并行
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/27 15:32
 * @level：
 */
public class StreamAPI_Test3 {
    public static void main(String[] args) {

        int max=1000000;
        List<String> values=new ArrayList<>(max);
        for (int i=0;i<max;i++){
            UUID uuid=UUID.randomUUID();
            values.add(uuid.toString());
        }
        long start=System.currentTimeMillis();
//        long count=values.stream().sorted().count();       //串行计算（在一个线程中依次执行）
        long count=values.parallelStream().sorted().count();  //并行计算（在多个线程中并行执行）
        System.out.println(count);
        long end=System.currentTimeMillis();
        long millis=end-start;
        System.out.println(millis);
    }
}
