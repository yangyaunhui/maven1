package com.xiexin.shirotest;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;

/*
* shiro的加密和认证测试
* */
public class MyShiro {
    //shiro有对明文加密123456,有加密功能,让web的密码更加安全
    @Test
    public void testCmd5(){
        Md5Hash md5Hash = new Md5Hash("123456");
        System.out.println("md5Hash = " + md5Hash);
    }
}
