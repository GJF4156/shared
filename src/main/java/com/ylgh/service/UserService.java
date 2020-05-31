package com.ylgh.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylgh.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 上善若水
 * @since 2020-05-30
 */
public interface UserService extends IService<User> {
    public Page<User> fuzzyQuery(int page,Integer limit, User user);

}
