package com.shiro.quickstartboot.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author: 无糖
 * @DateTime: 2021/5/20 19:22
 * 自定义Realm，需要继承AuthorizingRealm类
 */
public class MyRealm extends AuthorizingRealm {

    /**
     * 执行授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行认证"+token);
        //假设获取数据库用户名与密码
        String name = "admin";
        String password = "123456";
        //编写Shiro判断逻辑，判断用户名与密码
        //判断用户名
        UsernamePasswordToken tk = (UsernamePasswordToken) token;
        if (!tk.getUsername().equals(name)){
            System.err.println("用户名不存在！！！");
            return null;
        }
        //判断密码
        return new SimpleAuthenticationInfo("",password,"");
    }
}
