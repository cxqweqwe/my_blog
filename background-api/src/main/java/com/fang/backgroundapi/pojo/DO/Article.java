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
 * 博客表
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博客ID
     */
    @TableId(value = "article_id", type = IdType.ASSIGN_UUID)
    private String articleId;

    /**
     * 作者id
     */
    private String authorId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容简介
     */
    private String briefIntroduction;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签,多个标签使用英文逗号分隔
     */
    private String label;

    /**
     * 属性，0原创，1转载
     */
    private Integer attributes;

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
