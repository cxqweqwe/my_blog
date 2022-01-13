package com.fang.backgroundapi.service;

import com.fang.backgroundapi.pojo.DO.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
public interface UserInfoService {

    public Integer insertUserInfo(UserInfo userInfo);

    public Integer updateUserInfo(UserInfo userInfo);

    public Integer deleteUserInfo(String authorId);

    public UserInfo findUserInfoByAuthorId(String authorId);

    /* ***************   以上 基本方法   ******************  */

    public UserInfo findUserInfoByEmail(String email);

    public UserInfo findUserInfoByPhone(String phone);

}
