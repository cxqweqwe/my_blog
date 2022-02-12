package com.fang.backgroundapi.pojo.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 论坛的帖子信息
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PostInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 帖子ID
     */
    @TableId(value = "post_id", type = IdType.ASSIGN_UUID)
    private String postId;

    /**
     * 创建者ID
     */
    private String authorId;

    /**
     * 帖子名字
     */
    private String postName;

    /**
     * 借鉴帖子内容
     */
    private String postDescription;

    /**
     * 帖子有关图片
     */
    private String imagePath;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 最后一次修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date modifiedTime;

    /**
     * 0表示正常(可用)，1表示删除(不可用)
     */
    @TableLogic
    private Integer deleted;

    /**
     * 状态【0：违规，1：正常，2：等待审核】
     */
    private Integer state;


}
