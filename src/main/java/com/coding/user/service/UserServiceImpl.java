package com.coding.user.service;

import com.coding.user.dao.UserMapper;
import com.coding.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * Created by 平凡的世界 on 2018/4/26.
 */
@Component(value ="UserMapper")
@Service("UserServiceImpl")
public class UserServiceImpl  implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Override

    public int createUser(User user) {
        return userMapper.insertSelective(user);
    }
}
