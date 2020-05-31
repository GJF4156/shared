package com.ylgh.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ylgh.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 上善若水
 * @since 2020-05-30
 */
public interface UserMapper extends BaseMapper<User> {
    public List<User> fuzzyQuery(Page<User> page,@Param("user") User user);
}
