package com.coding.user.service;

import com.coding.login.controller.commons.utils.SecurityUtils;
import com.coding.user.dao.UserMapper;
import com.coding.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


/**
 * @author  没想法的岁月
 *  @Date $date $time
 * @description
 */

@Service("UserServiceImpl")
public class UserServiceImpl  implements UserService{

//    dbcTemplate将我们使用的JDBC的流程封装起来，包括了异常的捕捉、SQL的执行、查询结果的转换等等
    @Autowired
   private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int createUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return userMapper.insertSelective(user);
    }

    /**
     * @author  没想法的岁月
     * @Date 2018/5/1 9:31
     * @Description 根据用户名来查询用户
     */
    @Override
    public User findUserByUserName(String name) {
        User user =userMapper.selectByUserName(name);
        return user;
    }

    /**
     * @author  没想法的岁月
     * @Date 2018/5/1 12:18
     * @Description 注册新用户
     */

    @Override
    public void nLoginUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        user.setPassword(SecurityUtils.encrtyPassword(user.getPassword()));
        userMapper.insertSelective(user);
    }
}
