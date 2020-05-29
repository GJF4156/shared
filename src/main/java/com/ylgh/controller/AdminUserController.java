package com.ylgh.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ylgh.pojo.AdminUser;
import com.ylgh.service.AdminUserService;
import com.ylgh.utils.IpUtil;
import com.ylgh.utils.JsonMessage;
import com.ylgh.utils.MD5;
import com.ylgh.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 上善若水
 * @since 2020-05-15
 */
@RestController
@RequestMapping("/ylgh/admin-user")
public class AdminUserController {
    private static Logger logger = LoggerFactory.getLogger(AdminUserController.class);
    @Autowired
    private AdminUserService adminUserService;


    /**
     * @ 方法描述： 注册账号
     * @ 参数： user:用户注册信息json字符串
     * @ 返回类型： JsonMessage
     */
    @PostMapping(value = "/{user}", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonMessage registered(@PathVariable("user") String user) {
        AdminUser adminUser = JSON.parseObject(user, AdminUser.class);
        String adminPsw = adminUser.getAdminPsw();
        adminUser.setAdminPsw(MD5.string2MD5(adminPsw));
        logger.info("MD5加密密码：" + adminUser.getAdminPsw());
        boolean save = adminUserService.save(adminUser);
        if (save) {
            return JsonMessage.success();
        }
        return JsonMessage.error("注册失败！");
    }

    /**
     * @ 方法描述： 登陆账号
     * @ 参数： account:账号  password：密码
     * @ 返回类型： JsonMessage
     */
    @GetMapping(value = "/{account}/{password}")
    @ResponseBody
    public JsonMessage login(@PathVariable("account") String account,
                             @PathVariable("password") String password,
                             HttpServletRequest request) {
        //获取系统登录的IP
        String ip = IpUtil.getIpAddr(request);
        logger.info("用户登录IP：" + ip);
        //获取登陆系统的时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lastLoginTime = df.format(new Date());
        if (account != null && !account.equals("") && password != null && !password.equals("")) {
            QueryWrapper<AdminUser> wrapper = new QueryWrapper<>();
            wrapper.eq("admin_account", account);
            wrapper.eq("admin_psw", MD5.string2MD5(password));
            AdminUser one = adminUserService.getOne(wrapper);
            if (one != null) {
                one.setLoginIp(ip);
                one.setLastLoginTime(lastLoginTime);
                UpdateWrapper<AdminUser> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("admin_account", one.getAdminAccount());
                adminUserService.update(one, updateWrapper);
                logger.info("登陆成功：" + one.toString());
                String token = TokenUtil.createToken(one.getAdminAccount(), one.getAdminNickname());
                logger.info("TOKEN：" + token);
                return JsonMessage.success()
                        .add("token", token);
            } else {
                return JsonMessage.error("登陆失败!");
            }
        } else {
            return JsonMessage.error("账号密码不能为空！");
        }
    }

    /**
     * @ 方法描述： 更新用户信息
     * @ 参数： user:用户信息封装对象
     * @ 返回类型： JsonMessage
     */
    @PutMapping(value = "/{user}", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonMessage update(@PathVariable("user") String user) {
        AdminUser adminUser = JSON.parseObject(user, AdminUser.class);
        //将密码加密再存入数据库
        adminUser.setAdminPsw(MD5.string2MD5(adminUser.getAdminPsw()));
        UpdateWrapper<AdminUser> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("admin_account", adminUser.getAdminAccount());
        boolean update = adminUserService.update(userUpdateWrapper);
        if (update) {
            return JsonMessage.success();
        } else {
            return JsonMessage.error("修改失败！");
        }
    }

    /**
     * @ 方法描述： 删除用户
     * @ 参数： account：用户账号，根据账号删除用户
     * @ 返回类型： JsonMessage
     */
    @DeleteMapping(value = "/{account}")
    @ResponseBody
    public JsonMessage delete(@PathVariable("account") String account) {
        if (account != null && !account.equals("")) {
            QueryWrapper<AdminUser> wrapper = new QueryWrapper<>();
            wrapper.eq("admin_account", account);
            boolean remove = adminUserService.remove(wrapper);
            if (remove) {
                return JsonMessage.success();
            } else {
                return JsonMessage.error("删除失败！");
            }
        } else {
            return JsonMessage.error("参数不能为空！");
        }
    }

    /**
     * @ 方法描述： 校验用户是否存在
     * @ 参数： account：用户账号
     * @ 返回类型： JsonMessage
     */
    @GetMapping(value = "/{account}")
    @ResponseBody
    public JsonMessage checkAccountExist(@PathVariable("account") String account) {
        System.out.println(account);
        if (account != null && !account.equals("")) {
            QueryWrapper<AdminUser> wrapper = new QueryWrapper<>();
            wrapper.eq("admin_account", account);
            AdminUser one = adminUserService.getOne(wrapper);
            if (one != null) {
                return JsonMessage.error("该用户已注册，请直接登录！");
            } else {
                return JsonMessage.success();
            }
        } else {
            return JsonMessage.error("账号不能为空！");
        }
    }

    /**
     * @ 方法描述： 获取用户详细信息
     * @ 参数： 无
     * @ 返回类型：JsonMessage
     */
    @GetMapping(value = "/info")
    public JsonMessage getInfo(HttpServletRequest request) throws ParseException {
        String token = request.getHeader("Authorization");
        if (token != null && !token.equals("")) {
            logger.info("接入参数：" + token);
            Claims claims = TokenUtil.parseToken(token);
            String id = claims.getId();
            Date expiration = claims.getExpiration();
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(expiration);
            logger.info("过期时间：" + format);
            logger.info(id);
            QueryWrapper<AdminUser> wrapper = new QueryWrapper<>();
            wrapper.eq("admin_account", id);
            AdminUser one = adminUserService.getOne(wrapper);
            if (one != null) {
                return JsonMessage.success().add("data", one);
            } else {
                logger.info("校验码无效！");
                return JsonMessage.error("获取用户信息失败！");
            }
        }else {
            logger.info("空校验码！未登录。。。");
            return JsonMessage.error("还未登录，请先登录");
        }
    }
}

