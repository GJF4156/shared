package com.ylgh.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * @ 作者：上善若水
 * @ 时间：2020-05-31 16:14
 * @ 修改：于2020年05月31日更改
 * @ 描述：
 * @ 版本:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserVo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private String userPhone;

    private String userNick;

    private String userEmail;

    private Integer userSex;

    private String userAddress;

    private String userAutograph;

    private String lastLoginTime;

    private String loginIp;

    private Integer userStatus;


}