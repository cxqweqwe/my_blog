package com.fang.backgroundapi.pojo.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/2/12 22:45
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostInfoVO implements Serializable {

     private String postId;
     private String authorId;
     private String nickName;
     private String postName;
     private String postDescription;
     private String imagePath;
     @JsonFormat(pattern = "dd MMMM,yyyy",timezone = "US")
     private Date createTime;
     private String viewsCount;//暂时不用
     private String commonCount;

}
