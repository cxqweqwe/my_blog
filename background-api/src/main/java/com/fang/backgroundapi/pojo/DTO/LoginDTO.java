package com.fang.backgroundapi.pojo.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/8/18 16:51
 **/
@Data
public class LoginDTO implements Serializable {

    private String username;
    private String password;

}
