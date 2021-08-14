package com.fang.backgroundapi.pojo.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 博客评论表
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ArticleComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 博客ID,标注在哪条评论下面
     */
    private String articleId;

    /**
     * 评论者ID
     */
    private String authorId;

    /**
     * 状态【0：违规，1：正常，2：等待审核】
     */
    private Integer state;

    /**
     * 图片路径
     */
    private String iamgePath;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论回复，填写评论的ID
     */
    private String reply;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
    private Date modify;

    /**
     * 软删除
     */
    @TableLogic
    private Integer deleted;


}
