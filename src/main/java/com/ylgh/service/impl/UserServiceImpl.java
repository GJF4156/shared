package com.ylgh.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylgh.pojo.User;
import com.ylgh.dao.UserMapper;
import com.ylgh.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 上善若水
 * @since 2020-05-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<User> fuzzyQuery(int page,Integer limit, User user) {
        Page<User> page1=new Page<>(page,limit);
        List<User> users = userMapper.fuzzyQuery(page1, user);
        return page1.setRecords(users);
    }
}
