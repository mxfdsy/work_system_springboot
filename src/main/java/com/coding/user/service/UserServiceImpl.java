package com.coding.user.service;

import com.coding.user.dao.UserMapper;
import com.coding.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


/**
 * Created by 平凡的世界 on 2018/4/26.
 */

@Service("UserServiceImpl")
public class UserServiceImpl  implements UserService{

//    dbcTemplate将我们使用的JDBC的流程封装起来，包括了异常的捕捉、SQL的执行、查询结果的转换等等
    @Autowired
   private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapper userMapper;

    public int createUser(User user) {
        return userMapper.insertSelective(user);
    }
}
