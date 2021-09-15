package com.fang.backgroundapi.pojo.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/9/12 21:20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("博客简介信息展示类")
public class MostPopularInfoVO {

    @ApiModelProperty("博客ID")
    private String articleId;

    @ApiModelProperty("作者id")
    private String authorId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容简介")
    private String briefIntroduction;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date modifiedTime;

}
