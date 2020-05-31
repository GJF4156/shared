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
 * @since 2020-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String userPhone;

    private String userPassword;

    private String userNick;

    private String userHead;

    private String userEmail;

    private Integer userSex;

    private String userAddress;

    private String userAutograph;

    private String lastLoginTime;

    private String loginIp;

    private Integer userStatus;


}
