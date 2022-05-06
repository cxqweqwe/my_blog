package com.fang.backgroundapi.pojo.VO;

import lombok.Data;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/5/6 16:28
 **/
@Data
public class AdminForum {

    private String postId;
    private String authorId;
    private String postName;
    private String nickName;
    private Integer deleted;
    private Integer state;

}
