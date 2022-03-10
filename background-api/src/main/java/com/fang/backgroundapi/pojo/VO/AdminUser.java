package com.fang.backgroundapi.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
