package Java基础.代理模式.jdk;

import Java基础.代理模式.jdk.dao.UserDao;
import Java基础.代理模式.jdk.dao.impl.UserDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/17 13:09
 * @level：
 */
public class TestJdk {

    public static void main(String[] args) {
        UserDao userDao=new UserDaoImpl();
        //我现在要一个代理类来代理执行接口实现类的方法
        InvocationHandler invocationHandle=new MyInvocationHandle(userDao);
        UserDao proxyInstance=(UserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(),userDao.getClass().getInterfaces(),invocationHandle);
        proxyInstance.queryUser();
    }

}
