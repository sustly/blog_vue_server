package com.sustly.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author liyue
 * @date 2019/3/19 18:16
 */
public class Md5Util {
    public static String encrypt(String data){
        Md5Hash md5Hash = new Md5Hash(data,data,3);
        return md5Hash.toString();
    }
}