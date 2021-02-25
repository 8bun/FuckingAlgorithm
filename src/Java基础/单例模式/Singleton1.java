package Java基础.单例模式;

/**
 * @description： 双重检验锁，懒汉：做什么都要找理由 （ 即使运行创建对象，也要考虑多线程、安全，对象重复等问题）。
 *  是饱汉模式的优化，进行双重判断，当已经创建过实例对象后就无需加锁，提高效率。也是一种推荐使用的方式
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/14 11:32
 * @level：
 */
public class Singleton1 {
    /**
     * uniqueInstance 采用 volatile 关键字修饰也是很有必要的， uniqueInstance = new Singleton(); 这段代码其实是分为三步执行：
     * 为 uniqueInstance 分配内存空间
     * 初始化 uniqueInstance
     * 将 uniqueInstance 指向分配的内存地址
     * 但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。指令重排在单线程环境下不会出现问题，
     * 但是在多线程环境下会导致一个线程获得还没有初始化的实例。例如，线程 T1 执行了 1 和 3，此时 T2 调用 getUniqueInstance()
     * 后发现 uniqueInstance 不为空，因此返回 uniqueInstance，但此时 uniqueInstance 还未被初始化。
     *
     * 对象初始化的例子如：List<String> list;
     * list=new ArrayList<String>(); //这样就将其初始化了。然后你可以调用它的方法如：list.add("dsafs");//正确的调用方法。
     * 如果没有上述的初始化，list.add("dsafs")；//运行这句话时将要产生NullPointerException（空指针）异常。
     * 基本类型数据用着全局变量，声明后如果不初始化的话，
     * java虚拟机将自动对其初始化,比如你在类的成员变量中声明:int age;
     * //这里没有初始化，将产生默认值0;其他基本类型数据都有其默认值。但如果是声明局部变量，
     * 比如说在某个方法中，就必须先初始化再调用，如：
     * void add(){
     *       int a;
     *       System.out.println(a);//这样编译时不能通过，必须要求你先初始化
     * }
     * 也就说：如果对象初始化了，那么在方法中就可以直接用了。
     * 如果不初始化，那么在方法中必须先赋值，才能操作。
     * 不然就报错了
     */
    private volatile static Singleton1 singleton1;
    private Singleton1(){}
    public static Singleton1 getInstance(){
        //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        /**
         * 因为一旦singleton1被正确初始化以后，每次访问null == singleton1一定能够得到false从而不会重新初始化。
         * 但是我们无需每次都先通过上锁再来判断null == singleton1。
         * 所以双重锁的目的是为了成功初始化singleton1之后不再触发加锁操作。
         */
        if(singleton1==null){
            synchronized (Singleton1.class){
                if (singleton1==null){
                    singleton1=new Singleton1();
                }
            }
        }
        return singleton1;
    }
}
