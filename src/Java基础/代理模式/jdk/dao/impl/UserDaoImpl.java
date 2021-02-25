package Java基础.代理模式.jdk.dao.impl;

import Java基础.代理模式.jdk.dao.UserDao;

/**
 * @description：
 * @url：
 * @限制：
 * @author：Jack
 * @createTime：2020/3/17 13:10
 * @level：
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void queryUser() {
        System.out.println("查找用户");
    }
}
