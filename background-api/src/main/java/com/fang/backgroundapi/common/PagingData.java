package com.fang.backgroundapi.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/8/25 15:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("具有分页接口的过渡类")
public class PagingData {

    @ApiModelProperty("总数量")
    private Long total;
    @ApiModelProperty("具体数据")
    private Object data;

}
