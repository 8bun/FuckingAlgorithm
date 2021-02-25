package Java基础.单例模式;

/**
 * @description： 饱汉模式：不是线程安全的，因为是在需要的时候才会产生实例对象，
 * 生产之前会判断对象引用是否为空，这里，如果多个线程同时进入判断，就会生成多个实例对象，
 * 这是不符合单例的思想的。所以饱汉模式为了保证线程安全，
 * 就用synchronized关键字标识了方法。之所以被称为“饱汉”，因为它很饱，不急着生产实例，在需要的时候才会生产。
 * 好处：延时加载，用的时候才会生产对象。
 * 缺点：需要保证同步，付出效率的代价。
 * 饱汉模式，很饱不着急，延迟加载，啥时候用啥时候创建实例，存在线程安全问题
 * @url：
 * @限制：
 * @author：Jack
 * @createTime:  2020/3/14 12:08
 * @level：
 */
public class Singleton2 {
    
    private static Singleton2 singleton2;

    private Singleton2(){}

    public static Singleton2 getInstance(){
        if(singleton2==null){
            singleton2=new Singleton2();
        }
        return singleton2;
    }
}
