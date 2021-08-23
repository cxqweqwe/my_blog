package com.fang.backgroundapi.service;

import com.fang.backgroundapi.pojo.DO.ImageBase64;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 图片base64存储表 服务类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-23
 */
public interface ImageBase64Service {

    /**
     * @Description:
     * @Author: Bernie_fang
     * @Since: 2021/8/23 17:47
     * @param id:
     * @return: com.fang.backgroundapi.pojo.DO.ImageBase64
     **/
    public ImageBase64 findImageBase64ByID(String id);

    /**
     * @Description: 往数据库插入一条数据格式
     * @Author: Bernie_fang
     * @Since: 2021/8/23 17:47
     * @param imageBase64:
     * @return: java.lang.String  返回插入之后的ID主键
     **/
    public String insertImageBase64(ImageBase64 imageBase64);

}
