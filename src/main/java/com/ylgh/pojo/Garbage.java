package com.ylgh.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.interceptor.AroundConstruct;

/**
 * <p>
 * 
 * </p>
 *
 * @author 上善若水
 * @since 2020-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Garbage implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 垃圾名字
     */
    private String gname;

    /**
     * 垃圾类型
     */
    private Integer gtype;

    /**
     * 智能预判
     */
    private Integer aipre;

    /**
     * 分类解释
     */
    private String gexplain;

    /**
     * 包含类型
     */
    private String gcontain;

    /**
     * 投放提示
     */
    private String gtip;





}
