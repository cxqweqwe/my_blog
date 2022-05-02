package com.fang.backgroundapi.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/9/13 16:15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserInfoDTO implements Serializable {


    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID，服务器生成")
    @JsonProperty(value = "authorId")
    private String authorId;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    @JsonProperty(value = "nickName")
    private String nickName;

    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String realName;

    /**
     * 头像路径
     */
    @ApiModelProperty("头像路径")
    private String avatarPath;

    /**
     * 性别【男：1，女：0】
     */
    @ApiModelProperty("性别【男：1，女：0】")
    private String sex;

    /**
     * 所在城市
     */
    @ApiModelProperty("所在城市")
    private String city;

    /**
     * 个人简介
     */
    @ApiModelProperty("个人简介")
    private String personalProfile;

    /**
     * 开始工作日期
     */
    @ApiModelProperty("开始工作日期")
    private String workDate;

    /**
     * 所属学校(一般是大学)
     */
    @ApiModelProperty("所属学校(一般是大学)")
    private String school;

    /**
     * 专业
     */
    @ApiModelProperty("专业")
    private String major;

    /**
     * 入学时间
     */
    @ApiModelProperty("入学时间")
    private String admissionTime;

    /**
     * 公司名称
     */
    @ApiModelProperty("公司名称")
    private String companyName;

    /**
     * 职位名称
     */
    @ApiModelProperty("职位名称")
    private String positionName;

    /**
     * 所属行业
     */
    @ApiModelProperty("所属行业")
    private String industry;

}
