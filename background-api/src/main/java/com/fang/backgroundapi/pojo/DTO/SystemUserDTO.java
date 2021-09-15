package com.fang.backgroundapi.pojo.DTO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/9/13 17:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("注册类")
public class SystemUserDTO {
    /**
     * 作者id，全局唯一
     */
    private String authorId;

    /**
     * 用户名，登录使用
     */
    private String username;

    /**
     * 密文，验证密码用这个
     */
    private String password;

}
