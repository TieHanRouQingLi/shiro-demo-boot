package com.lhj.shiro.chapter2;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * @author lhj
 * @Classname MyRealm2
 * @Description
 * @Date 2021/1/8 14:51
 * @Version V1.0
 */
public class MyRealm2 implements Realm {
    @Override
    public String getName() {
        return "myrealm2";

    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        //仅支持UsernamePasswordToken类型的Token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //得到用户名
        String username = (String) authenticationToken.getPrincipal();
        //得到密码
        String password = new String((char[]) authenticationToken.getCredentials());
        if (!"li".equals(username)) {
            //如果用户名错误
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)) {
            //如果密码错误
            throw new IncorrectCredentialsException();
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
