package com.ylgh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ 作者：上善若水
 * @ 时间：2020-05-14 16:10
 * @ 描述：页面跳转控制器
 * @ 修改： 于 年 月 日更改
 * @ 版本:
 */
@Controller
public class PageController {
    @RequestMapping(value = "/index")
    public String toIndex() {
        return "index";
    }

    @RequestMapping(value = "/registered")
    public String toRegistered() {
        return "registered";
    }

    @RequestMapping(value = "/login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/admin")
    public String toAdmin() {
        return "admin";
    }

    @RequestMapping(value = "/user_manager")
    public String toUserManager() {
        return "user_manager";
    }

    @RequestMapping(value = "/test2")
    public String toTest2() {
        return "test2";
    }


}
