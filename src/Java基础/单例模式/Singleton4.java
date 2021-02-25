package Java基础.单例模式;

/**
 * @description： 静态内部类的单例模式
 *原理：通过一个静态内部类定义一个静态变量来持有当前类实例，在类加载时就创建好，在使用时获取。
 * 缺点：无法做到延迟创建对象，在类加载时进行创建会导致初始化时间变长。
 * @url：
 * @限制：
 * @author：Jack
 * @createTime:  2020/3/14 12:08
 * @level：
 */
public class Singleton4 {
    private static class Holder {
      private static Singleton4 singleton4 = new Singleton4();
    }
    private Singleton4() {}
    public static Singleton4 getSingleton4() {
        return Holder.singleton4;
    }
}

