package com.xiexin.controller;


import com.xiexin.bean.Admin;
import com.xiexin.respcode.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/loginByShiro")
    public Result loginByShiro(@RequestBody Admin admin){
        System.out.println("admin = " + admin.getAdminAccount());
        //登录交给shiro和securitymanger管理
        Subject subject = SecurityUtils.getSubject();//subject 是根据 过滤器拿到的
        UsernamePasswordToken token = new UsernamePasswordToken(admin.getAdminAccount(),admin.getAdminPwd() );
        try {
            subject.login(token);//ok
            return new Result();
        } catch (Exception e) {//登录不对
            e.printStackTrace();
            return new Result(4001,"账号或者密码不正确");
        }
    }
}
