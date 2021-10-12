package com.xiexin.shirotest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

/*
* shiro的认证
* */
public class ShiroIni {
    //shiro三大概念
    //1.subject(用户的请求,主体)
    //2.security Manager(shiro的管理类)
    //3.realms(数据库)
    //realms分为:ini realm,jdbc realm,自定义的realm---常用自定义(mybatis)
    @Test
    public void test01(){
        //realms(数据库)
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        //security Manager(shiro的管理类)
        DefaultSecurityManager sm = new DefaultSecurityManager();
        sm.setRealm(iniRealm);
        //以上步骤指的是把realms注入到sm中,即它们两个联系在一起,下面就剩下subject了
        //subje不是new的,因为subject是一个实打实的对象,原本就有的!!!
        //只需要用shiro的类做一个接待就可以
        SecurityUtils.setSecurityManager(sm);//接管sm
        Subject subject = SecurityUtils.getSubject();
        //就可以使用subject了
        //拟定一个虚拟的账户和密码
        String userName="xiexin";
        String userPwd="123";
        //在这里,利用shiro把userName和userPwd变为一个token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, userPwd);
        System.out.println("顾客登录的时候把账户和密码加工后的token=" + usernamePasswordToken);

        UsernamePasswordToken dbToken = new UsernamePasswordToken("xiexin", "123");
        System.out.println("数据库中把账户和密码加工后的token=" + dbToken);

        //注意:这个登录的方法是shiro提供的,以后我们自己不写登录
        //IncorrectCredentialsException 密码错误
        //UnknownAccountException 账户错误
        try {
            subject.login(usernamePasswordToken);
            System.out.println("登录成功");
        }catch (UnknownAccountException e){
            System.out.println("账户错误");
            e.printStackTrace();
        }catch (IncorrectCredentialsException e){
            System.out.println("密码不对");
            e.printStackTrace();
        }

    }
}
