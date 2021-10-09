package com.xiexin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//springboot项目是为了简化ssm项目而存在的
//ssm项目配置比较繁琐,比如需要配置tomcat,需要多个xml
//而springboot简化成,该内置的就内置,多个xml配置改为一个properties / yml文件
//说白了还是ssm框架,只不过写起来简单了
@SpringBootApplication //springboot应用注解,标记本项目是springboot项目.必须有这个注解
@MapperScan("com.xiexin.dao")//持久层的dao包扫描
public class SmartfoodApplication {
    //main方法,项目一启动就会执行该方法
    public static void main(String[] args) {
        //静态的调用SpringApplication应用,参数为本项目的启动类
        System.out.println("SpringBoot启动了");
        SpringApplication.run(SmartfoodApplication.class, args);
    }

}
