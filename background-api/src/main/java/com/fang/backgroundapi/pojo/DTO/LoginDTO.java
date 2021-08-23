package com.fang.backgroundapi.pojo.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/8/18 16:51
 **/
@Data
@ApiModel("登录信息类")
public class LoginDTO implements Serializable {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名打算不能为空")
    private String username;
    @ApiModelProperty("密码")
    @NotBlank(message = "密码阿斯弗不能为空")
    private String password;

}
