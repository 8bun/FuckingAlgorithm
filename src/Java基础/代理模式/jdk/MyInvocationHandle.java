package Java基础.代理模式.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/17 13:25
 * @level：
 */
public class MyInvocationHandle implements InvocationHandler {

    //被代理的目标对象
    Object target;

    public MyInvocationHandle(Object target){
        super();
        this.target=target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("增强代码");
        Object returnVal=method.invoke(target,args);  //代理类执行被代理的方法，需要target
//        System.out.println(returnVal);
        return returnVal;
    }
}
