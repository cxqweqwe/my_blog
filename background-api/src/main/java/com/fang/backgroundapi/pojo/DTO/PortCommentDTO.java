package com.fang.backgroundapi.pojo.DTO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 论坛评论表
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PortCommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    private String id;

    /**
     * 博客ID,标注在哪条评论下面
     */
    private String portId;

    /**
     * 评论者ID
     */
    private String authorId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 图片路径
     */
    private String imagePath;

    /**
     * 状态【0：违规，1：正常，2：等待审核】
     */
    private Integer state;


}
