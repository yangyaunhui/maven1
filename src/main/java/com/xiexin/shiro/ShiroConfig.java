package com.xiexin.shiro;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 的web配置
 * 目的： 因为shiro可以和 很多 项目适配， 那么我们是web项目，就需要 配置成web 的 securityManger
 * ， 又 因为是 web项目， 所以 需要使用  过滤器 来  配置  需要拦截的请求，和非拦截的请求，
 *
 */
@Configuration  // 配置类的注解,表明该类是配置类,该注解是配置的意思,顶替的是xml中的配置。
            // 优先于其他注解优先执行
public class ShiroConfig {
    // 1. shiroconfig需要指明Realm是谁,并且把这个realm创建出来,这个创建指的是优先于其他的controller,service等
    // 对象优先创建
    @Bean
    public Realm getMybatisRealm(){
        MybatisRealm realm = new MybatisRealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1024);
        realm.setCredentialsMatcher(matcher); // 注入匹配，  注入 加密加盐的匹配
        return realm;
    }

    // 2.指派securityManager,因为是我们是web项目所以是websecurityManager
    @Bean
    public DefaultWebSecurityManager getSecurityManager (Realm realm){
         DefaultWebSecurityManager sm= new DefaultWebSecurityManager();
         sm.setRealm(realm);
         return sm;
    }
    // 3.subject需要用过滤器来获取
    @Bean
    public ShiroFilterFactoryBean getFilter(DefaultWebSecurityManager sm){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(sm);
        // 使用 过滤器
        Map map = new LinkedHashMap<>(); // 这个map是有序的
        // 不拦截的页面！！！
        map.put("/page/login","anon");  // anon匿名的,任何请求都可以去访问
        map.put("/admin/loginByShiro","anon"); //登录的方法也不拦截
        map.put("/*/**","authc");   // authc 需要登录
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map); // 把拦截的顺序放入到linkedmap中
        return shiroFilterFactoryBean;
    }


}
