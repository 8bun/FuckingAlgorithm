package Java基础.代理模式.cglib;

import Java基础.代理模式.cglib.Bean.User;
import Java基础.代理模式.cglib.callback.MyCallBackMethod;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/17 13:09
 * @level：
 */
public class TestCglib {

    public static void main(String[] args) {

        //代理User类执行方法
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(User.class); //装载被代理类

        Callback callback=new MyCallBackMethod();
        enhancer.setCallback(callback);

        User user=(User)enhancer.create();
        user.login();
    }
}
