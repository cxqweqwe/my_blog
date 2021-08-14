package com.fang.backgroundapi.pojo.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作者id，全局唯一
     */
    @TableId(value = "author_id", type = IdType.ASSIGN_UUID)
    private String authorId;

    /**
     * 用户名，登录使用
     */
    private String username;

    /**
     * 密文，验证密码用这个
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后一次修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;

    /**
     * 0表示正常(可用)，1表示删除(不可用)
     */
    @TableLogic
    private Integer deleted;


}
