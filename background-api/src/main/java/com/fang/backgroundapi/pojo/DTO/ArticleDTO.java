package com.fang.backgroundapi.pojo.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/8/25 15:18
 **/
@Data
@ApiModel("博客信息接收类")
public class ArticleDTO implements Serializable {

    @ApiModelProperty("博客ID")
    private String articleId;

    @ApiModelProperty("作者id")
    private String authorId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容简介")
    private String briefIntroduction;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("内容的html格式")
    private String html;

    @ApiModelProperty("标签,多个标签使用英文逗号分隔")
    private String label;

    @ApiModelProperty("属性，0原创，1转载")
    private Integer attributes;

    @ApiModelProperty("属性，0未发布，1已发布")
    private Integer releaseOrNot;

    @ApiModelProperty("封面路径")
    private String coverPath;

}
