package com.coding.user.service;

import com.coding.user.entity.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 平凡的世界 on 2018/4/26.
 */
public interface UserService {

    int createUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    User findUserByUserName(String name);

    void nLoginUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
