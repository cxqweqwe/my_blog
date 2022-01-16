package com.fang.backgroundapi.pojo.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fang.backgroundapi.typehandler.Encrypt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/1/16 10:36
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
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
    private String phoneNumber;

    /**
     * 用户邮箱
     */
    private String email;

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
}
