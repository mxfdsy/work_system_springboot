package com.coding.commons.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 平凡的世界 on 2018/4/29.
 */
public class SecurityUtils {

    /**
     * 加密密码
     * @param password 密码
     * @return  加密后的密码
     * @throws NoSuchAlgorithmException 没有这样的加密算法
     * @throws UnsupportedEncodingException 解码异常
     */
    public static String encrptyPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //获取使用哪种算法加密
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        //BASE64Encoder 防止存进数据库的是乱码
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //对字符串进行加密
        String result = base64Encoder.encode(md5.digest(password.getBytes("utf-8")));
        return result;
    }

    /**
     * 校验加密后的密码和数据库密码是否一致
     * @param inputPwd 输入的密码
     * @param dbPwd 数据库的密码
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static boolean cheackPassword(String inputPwd, String dbPwd) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String result = encrptyPassword(inputPwd);
        if (result.equals(dbPwd)) {
            return true;
        } else {
            return  false;
        }
    }
}
