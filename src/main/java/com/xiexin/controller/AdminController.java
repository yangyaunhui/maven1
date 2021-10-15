package com.xiexin.controller;

import com.xiexin.bean.Admin;
import com.xiexin.respcode.Result;
import com.xiexin.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController{


    @Autowired
    private AdminService adminService;
    @RequestMapping("/reg")
    public Result reg(@RequestBody Admin admin){
        String adminPwdMingWen = admin.getAdminPwd();//没加密的密码
        //随机的几位字母作为salt
        //需要生成几位
        int n = 7;
        //最终生成的字符串
        String str = "";
        for (int i = 0; i < n; i++) {
            str = str + (char)(Math.random()*26+'a');
        }

        System.out.println(str);
        Md5Hash md5Pwd= new Md5Hash(adminPwdMingWen, str,1024);
        System.out.println("md5Pwd = " + md5Pwd);
        admin.setAdminPwd(md5Pwd.toString());
        admin.setSalt(str);

        int i = adminService.insertSelective(admin);//传入的是明文
        if (i==1){
            return new Result();
        }else{
            return new Result(4001,"注册失败,账户名可能存在");
        }

    }

    @RequestMapping("/loginByShiro")
    public Result loginByShiro(@RequestBody Map map) {
        System.out.println("adminAccount= " +map.get("adminAccount"));
        //登录交给shiro的securitManager管理
        Subject subject = SecurityUtils.getSubject();//subject是根据过滤器拿到的
        UsernamePasswordToken token = new UsernamePasswordToken((String)map.get("adminAccount"),(String) map.get("adminPwd"));

        try {
            subject.login(token);//ok
//                if ((Boolean) map.get("remeberMe")){
//                    token.setRememberMe(true);
//                }
            return new Result();
        } catch (UnknownAccountException e) { //账号错误
            e.printStackTrace();
            return new Result(40001,"账号不对");
        } catch (IncorrectCredentialsException e) { //密码错误
            e.printStackTrace();
            return new Result(40002,"密码不对");
        }
    }

    //shiro的退出 登录是shiro管理,退出也是shiro管理
    @RequestMapping("/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result();
    }
}
