package com.fang.backgroundapi.pojo.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 是否允许注册【1：允许，0：不允许】
     */
    private Integer allowRegister;

    /**
     * 是否允许使用邮箱注册【1：允许，0：不允许】
     */
    private Integer allowEmail;

    /**
     * 是否允许使用手机号注册【1：允许，0：不允许】
     */
    private Integer allowPhone;

    /**
     * 最后修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;


}
