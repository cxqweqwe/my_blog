package com.fang.backgroundapi.pojo.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/8/23 17:55
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("图片Base64接收类")
public class ImageBase64DTO {

    @ApiModelProperty("ID,服务器提供(若无，则不管)")
    private String id;

    @ApiModelProperty("图片Base64编码格式")
    @NotBlank(message = "图片Base64编码不允许为空")
    private String base64;

    @ApiModelProperty("图片名字")
    private String imageName;

    @ApiModelProperty("图片扩展名(png,jpg等)")
    @NotBlank(message = "图片扩展名不允许为空")
    private String fileExtName;

}
