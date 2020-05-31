package com.ylgh.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.glassfish.gmbal.ParameterNames;
import com.ylgh.pojo.AdminUser;
import com.ylgh.pojo.LayData;
import com.ylgh.pojo.User;
import com.ylgh.pojo.vo.UserVo;
import com.ylgh.service.UserService;
import com.ylgh.utils.JsonMessage;
import com.ylgh.utils.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 上善若水
 * @since 2020-05-30
 */
@RestController
@RequestMapping("/ylgh/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    @PostMapping
    public JsonMessage insert() {
        User user = new User();
        //获取登陆系统的时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lastLoginTime = df.format(new Date());
        user.setUserPassword(MD5.string2MD5("123456"));
        user.setUserHead("");
        user.setUserEmail("zs@qq.com");
        user.setUserSex(0);
        user.setUserAddress("四川绵阳");
        user.setUserAutograph("测试签名");
        user.setLastLoginTime(lastLoginTime);
        user.setLoginIp("192.168.0.1");
        user.setUserStatus(0);
        for (int i = 0; i < 100; i++) {
            user.setUserPhone("41701000" + i);
            user.setUserNick("测试" + i);
            userService.save(user);
        }
        return JsonMessage.success();
    }

    @RequestMapping("/get")
    @ResponseBody
    public LayData select(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        IPage<User> users = userService.page(new Page<User>(page, limit));
        LayData layData = new LayData();
        if (users != null) {
            layData.setCode(0);
            layData.setCount((int) users.getTotal());
            layData.setMsg("查询成功");
            layData.setData(users.getRecords());
        }
        return layData;
    }

    @RequestMapping("/selectByPhone")
    @ResponseBody
    public LayData selectByPhone(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "limit", defaultValue = "5") Integer limit,
                                 @RequestParam(value = "user") String user) {
        User user1 = JSON.parseObject(user, User.class);
        logger.info(user1.toString());
        Page<User> userPage = userService.fuzzyQuery(page, limit, user1);
        LayData layData = new LayData();
        layData.setCode(0);
        layData.setCount((int) userPage.getTotal());
        layData.setMsg("查询成功");
        layData.setData(userPage.getRecords());
        return layData;
    }

    @DeleteMapping(value = "/{phone}")
    @ResponseBody
    public JsonMessage delete(@PathVariable("phone") String phone){
        if (phone!=null && !phone.equals("")){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("user_phone", phone);
            boolean remove = userService.remove(wrapper);
            if (remove){
                return JsonMessage.success();
            }else {
                return JsonMessage.error("操作失败！请稍后再试...");
            }
        }else {
            return JsonMessage.error("操作失败！");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public JsonMessage update(UserVo user){
        logger.info(user.toString());
        User u=new User();
        u.setUserPhone(user.getUserPhone());
        u.setUserNick(user.getUserNick());
        u.setUserEmail(user.getUserEmail());
        u.setUserSex(user.getUserSex());
        u.setUserAddress(user.getUserAddress());
        u.setUserAutograph(user.getUserAutograph());
        u.setLastLoginTime(user.getLastLoginTime());
        u.setLoginIp(user.getLoginIp());
        u.setUserStatus(user.getUserStatus());
        logger.info("修改用户信息："+u.toString());
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("user_phone", u.getUserPhone());
        boolean update = userService.update(u, userUpdateWrapper);
        if (update){
            return JsonMessage.success();
        }else {
            return JsonMessage.error("更新失败！");
        }
    }


}

