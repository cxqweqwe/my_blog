package com.fang.backgroundapi.pojo.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fang.backgroundapi.typehandler.Encrypt;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "author_id", type = IdType.ASSIGN_ID)
    private String authorId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 头像路径
     */
    private String avatarPath;

    /**
     * 性别【男：1，女：0】
     */
    private String sex;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 用户手机号
     */
    private Encrypt phoneNumber;

    /**
     * 用户邮箱
     */
    private Encrypt email;

    /**
     * 个人简介
     */
    private String personalProfile;

    /**
     * 开始工作日期
     */
    private String workDate;

    /**
     * 所属学校(一般是大学)
     */
    private String school;

    /**
     * 专业
     */
    private String major;

    /**
     * 入学时间
     */
    private String admissionTime;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改信息时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date modifiedTime;

    /**
     * 0表示删除(不可用)，1表示正常
     */
    @TableLogic
    private Integer deleted;

    public UserInfo(String authorId) {
        this.authorId = authorId;
    }

}
