package com.coding.login.controller.commons.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密密码
 * Created by 没想法的岁月 on 2018/4/30.
 */
public class SecurityUtils {
    public  static  String encrtyPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String result = base64Encoder.encode(md5.digest(password.getBytes("utf-8")));
        return result;
    }

    public  static boolean checkPassword(String password,String dbPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String result = encrtyPassword(password);
        if (result.equals(dbPassword)) {
            return true;
        }else {
            return false;
        }
    }
}
