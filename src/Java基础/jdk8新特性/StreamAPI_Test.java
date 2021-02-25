package Java基础.jdk8新特性;

import java.util.ArrayList;
import java.util.List;

/**
 * @description：StreamAPL针对集合或数组进行操作，关注的是做什么，而不是怎么做
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/27 14:44
 * @level：
 */
public class StreamAPI_Test {

    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("hello");
        list.add("world");
        long cnt=0;
        for (String s:list){
            if(s.length()>3)
                cnt++;
        }
        System.out.println(cnt);

        //下面使用StreamAPI实现上面一样的功能（集合或数组的对象->对对象进行处理）
        //链式编程
        //函数式接口
        //lambda表达式
        //list.stream()转化为流
        long count = list.stream().filter((s) -> s.length() > 3).count(); //参数只有一个，可以省去()，filter内部
        //是否匹配，匹配则通过过滤，count+1
        System.out.println(count);
    }
}
