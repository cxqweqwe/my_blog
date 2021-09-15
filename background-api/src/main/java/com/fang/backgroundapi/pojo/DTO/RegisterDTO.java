package com.fang.backgroundapi.pojo.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/9/15 11:41
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("注册信息接收类")
public class RegisterDTO {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码必能为空")
    private String password;

    @ApiModelProperty("邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty("邮箱验证码")
    @NotBlank(message = "邮箱验证码不能为空")
    private String emailCaptcha;

}
