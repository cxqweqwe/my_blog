// package com.fang.backgroundapi.service.impl;
//
// import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
// import com.fang.backgroundapi.common.ServerResponse;
// import com.fang.backgroundapi.pojo.DO.ImageBase64;
// import com.fang.backgroundapi.mapper.ImageBase64Mapper;
// import com.fang.backgroundapi.pojo.DTO.ImageBase64DTO;
// import com.fang.backgroundapi.service.ImageBase64Service;
// import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
// import org.springframework.beans.BeanUtils;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
//
// /**
//  * <p>
//  * 图片base64存储表 服务实现类
//  * </p>
//  *
//  * @author Bernie_fang
//  * @since 2021-08-23
//  */
// @Service
// public class ImageBase64ServiceImpl implements ImageBase64Service {
//
//     @Autowired
//     private ImageBase64Mapper imageBase64Mapper;
//
//     @Override
//     public ImageBase64 findImageBase64ByID(String id) {
//         QueryWrapper<ImageBase64> wrapper = new QueryWrapper<>();
//         wrapper.eq("id", id);
//         ImageBase64 imageBase64 = imageBase64Mapper.selectOne(wrapper);
//         return imageBase64;
//     }
//
//     @Override
//     public String insertImageBase64(ImageBase64 imageBase64) {
//         int insert = imageBase64Mapper.insert(imageBase64);
//         return imageBase64.getId();
//     }
//
//     /**
//      * @Description: 处理上传的图片base64编码，吸入数据库，并返回写入的ID
//      * @Author: Bernie_fang
//      * @Since: 2021/8/23 18:03
//      * @param imageBase64DTO:
//      * @return: com.fang.backgroundapi.common.ServerResponse
//      **/
//     public ServerResponse uploadImageBase64(ImageBase64DTO imageBase64DTO) {
//         ImageBase64 base64 = new ImageBase64();
//         BeanUtils.copyProperties(imageBase64DTO, base64);
//         String id = insertImageBase64(base64);
//         return ServerResponse.success(2000,"操作成功", id);
//     }
//
//     public void getBase64ToPicture(String id){
//
//     }
//
// }
