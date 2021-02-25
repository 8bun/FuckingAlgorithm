package Java基础.jdk8新特性;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/27 15:09
 * @level：
 */
public class StreamAPI_Test2 {
    public static void main(String[] args) {
        List<User> users=new ArrayList<>();
        users.add(new User("a",20,3000));
        users.add(new User("b",30,4000));
        users.add(new User("c",40,5000));
        users.add(new User("d",40,6000));
        //查询年龄超过30岁的用户，保存在Stream容器
        Stream<User> userStream=users.stream().filter(e->e.getAge()>30);
        userStream.forEach(e-> System.out.println(e));

        //去重,自动调用equals和hashCode()方法
        System.out.println(users.stream().distinct().count());

        //跳过第一个
        Stream<User> skipStream=users.stream().skip(1);
        skipStream.forEach(System.out::println); //输出元素

        //获取第一个
        Stream<User> limitStream=users.stream().limit(1);

        //映射，拿到元素属性处理
        Stream<String> mapStream = users.stream().map(e -> e.getUsername().toUpperCase());
        mapStream.forEach(System.out::println);

        //排序
        Stream<Integer> ageStream= users.stream().map(User::getAge).sorted();
        ageStream.forEach(System.out::println);

        //定制排序
        Stream<User> userStream2= users.stream().sorted((x,y)->{
            if(x.getAge()==y.getAge()){
                return x.getUsername().compareTo(y.getUsername());  //年龄一样比较名字
            }else {
                return x.getAge()-y.getAge();
            }
        });
        userStream2.forEach(System.out::println);


        //规则匹配
        boolean b1=users.stream().allMatch(e->e.getAge()>30);  //是否都大于30岁的元素
        boolean b2=users.stream().anyMatch(e->e.getAge()>30);  //是否有至少一个大于30岁的元素

        Optional<User> max_age_user=users.stream().max((x, y)->x.getAge()-y.getAge());  //比较User大小，自定义为比较年龄，所以返回最大年龄的元素
        System.out.println(max_age_user.get());

    }
}
