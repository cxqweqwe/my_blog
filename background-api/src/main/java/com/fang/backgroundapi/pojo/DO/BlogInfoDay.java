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
 * 博客信息统计，每天
 * </p>
 *
 * @author Bernie_fang
 * @since 2022-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BlogInfoDay implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户ID
     */
    private String authorId;

    /**
     * 浏览量
     */
    private Integer pageviews;

    /**
     * 点赞量
     */
    private Integer likes;

    /**
     * 订阅量
     */
    private Integer subscription;

    /**
     * 评论量
     */
    private Integer comment;

    /**
     * 收藏量
     */
    private Integer collection;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;

    /**
     * 软删除
     */
    @TableLogic
    private Integer deleted;


}
