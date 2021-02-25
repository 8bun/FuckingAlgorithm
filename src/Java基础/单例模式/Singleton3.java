package Java基础.单例模式;

/**
 * @description： 饿汉：系统一运行就创建对象，不考虑太多的问题，所以可能会被创建多次，存在多个对象。
 *  饿汉模式是线程安全的，因为实例对象在类加载过程中就会被创建，在getInstance()方法中只是直接返回对象引用。
 *  之所以被称为“饿汉”，是因为这种模式创建实例对象比较“急”，真的是饿坏了……
 * 好处：简单明了，无需关注线程安全问题。
 * 缺点：如果在一个大环境下使用了过多的饿汉单例，则会生产出过多的实例对象，无论你是否要使用他们。
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/14 12:00
 * @level：
 */
public class Singleton3 {
    private static final Singleton3 singleton3=new Singleton3();
    private Singleton3(){}
    public static Singleton3 getInstance(){
        return singleton3;
    }
}
