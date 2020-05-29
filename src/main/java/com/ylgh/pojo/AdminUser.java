package com.ylgh.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 上善若水
 * @since 2020-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdminUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id索引
     */
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;

    /**
     * 登陆账号（唯一）
     */
    private String adminAccount;

    /**
     * 登录密码（MD5加密）
     */
    private String adminPsw;

    /**
     * 用户昵称
     */
    private String adminNickname;

    /**
     * 用户头像
     */
    private String adminHead;

    /**
     * 性别（0：男 1：女）
     */
    private Integer adminSex;

    /**
     * 上一次登陆时间
     */
    private String lastLoginTime;

    /**
     * 登录账号IP地址
     */
    private String loginIp;

    /**
     * 账号状态（0：禁用 1：正常）
     */
    private Integer adminStatus;


}
