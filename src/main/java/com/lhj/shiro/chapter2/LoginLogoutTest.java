package com.lhj.shiro.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * @author lhj
 * @Classname LoginLogoutTest
 * @Description
 * @Date 2021/1/7 11:44
 * @Version V1.0
 */
public class LoginLogoutTest {
    @Test
    public void testHelloworld() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份    验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "1233");

        try {
            //4、登录，即身份验证
            subject.login(token);
            System.out.println(subject.isAuthenticated());
        } catch (AuthenticationException e) {
            //5、身份验证失败
            System.out.println("认证失败:\t" + e.getMessage());
        }

        //断言用户已经登录
//        Assert.assertEquals(true, subject.isAuthenticated());

        //6、退出
        subject.logout();
    }


    @Test
    public void testCustomRealm() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4、登录，即身份验证
            subject.login(token);
            System.out.println("身份认证成功！");
        } catch (AuthenticationException e) {
            //5、身份验证失败
            System.out.println("身份认证失败：\t" + e.getMessage());
        }

        //6、退出
        subject.logout();
    }


    @Test
    public void testMultiCustomRealm() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("li", "1234");

        try {
            //4、登录，即身份验证
            subject.login(token);
            System.out.println("身份认证成功！");
        } catch (AuthenticationException e) {
            //5、身份验证失败
            System.out.println("身份认证失败：\t" + e.getMessage());
        }

        //6、退出
        subject.logout();
    }

}
