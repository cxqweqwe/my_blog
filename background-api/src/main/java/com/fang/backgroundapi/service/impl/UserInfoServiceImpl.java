package com.fang.backgroundapi.service.impl;

import com.fang.backgroundapi.pojo.DO.UserInfo;
import com.fang.backgroundapi.mapper.UserInfoMapper;
import com.fang.backgroundapi.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
