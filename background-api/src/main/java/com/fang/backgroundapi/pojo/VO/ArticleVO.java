package com.fang.backgroundapi.pojo.VO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/1/24 22:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 博客ID
     */
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
     * 内容的html格式
     */
    private String html;

    /**
     * 标签,多个标签使用英文逗号分隔
     */
    private String label;

    /**
     * 属性，0原创，1转载
     */
    private Integer attributes;

    /**
     * 属性，0未发布，1已发布
     */
    private Integer releaseOrNot;
    /**
     * 封面路径
     */
    private String coverPath;
    /**
     * 作者头像
     */
    private String avatarPath;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "dd MMMM,yyyy",timezone = "US")
    private Date modifiedTime;


}
