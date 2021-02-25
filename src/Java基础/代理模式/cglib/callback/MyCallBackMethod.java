package Java基础.代理模式.cglib.callback;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/17 13:46
 * @level：
 */
public class MyCallBackMethod implements MethodInterceptor {

    /**
     *
     * @param o  代理对象
     * @param method  目标对象中的方法
     * @param objects  目标对象方法的参数
     * @param methodProxy  代理对象中的代理方法（对象）
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("增强代码");
        Object returnVal = methodProxy.invokeSuper(o,objects);
        return returnVal;
    }
}
