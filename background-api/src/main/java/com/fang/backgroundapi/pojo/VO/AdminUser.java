package com.fang.backgroundapi.pojo.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/3/10 21:57
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUser {

    private String authorId;
    private String nickName;
    private Integer deleted;    // sysUser表的删除状态
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private String role;

}
