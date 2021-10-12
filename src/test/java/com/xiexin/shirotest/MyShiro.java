package com.xiexin.shirotest;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;

/*
* shiro的加密和认证测试
* */
public class MyShiro {
    //shiro有对明文加密123456,有加密功能,让web的密码更加安全
    //md5加密,简单,但是不可逆,可以根据加密后的密码进行反馈
    //更加的安全,就需要加盐!!!salt
    @Test
    public void testCmd5(){
        Md5Hash md5Hash = new Md5Hash("123456");//e10adc3949ba59abbe56e057f20f883e
        System.out.println("md5Hash = " + md5Hash);

        //给密码加盐,更安全
        Md5Hash md5Hash1 = new Md5Hash("123456", "shadouxing");//5ebd445c3627b67d44c3b44bed4305bf
        System.out.println("md5Hash1 = " + md5Hash1);

        //给加盐后的密码进行散列处理
        Md5Hash md5Hash2 = new Md5Hash("123456", "shadouxing",1024);//59ca96168448fd9727504b23927261fe
        System.out.println("md5Hash2 = " + md5Hash2);

        Md5Hash md5Hash3 = new Md5Hash("123456", "MGH7kU1(",1024);
        System.out.println("md5Hash3 = " + md5Hash3);

    }
}
