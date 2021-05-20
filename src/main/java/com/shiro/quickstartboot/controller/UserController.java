package com.shiro.quickstartboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 无糖
 * @DateTime: 2021/5/20 19:33
 */
@Controller
public class UserController {

    /**
     * 设置登录跳转页面
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        System.out.println("UserController.toLogin");
        return "login";
    }

    /**
     * 登录逻辑处理
     */
    @RequestMapping("/login")
    public String login(String name, String password, Model model) {
        //获取Shiro编写认证操作
        //1、获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //执行登录方法
        try {
            //登录成功
            subject.login(token);
            //跳转到success.html
            return "success";
        } catch (UnknownAccountException e) {
            //登录失败: 用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
            //登录失败: 密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    @RequestMapping("/add")
    public String add() {
        System.out.println("UserController.add");
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update() {
        System.out.println("UserController.update");
        return "/user/update";
    }
}
