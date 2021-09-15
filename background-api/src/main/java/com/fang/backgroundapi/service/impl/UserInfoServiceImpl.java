package com.fang.backgroundapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.pojo.DO.UserInfo;
import com.fang.backgroundapi.mapper.UserInfoMapper;
import com.fang.backgroundapi.pojo.VO.UserInfoDTO;
import com.fang.backgroundapi.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fang.backgroundapi.utils.SnowflakeIdWorker;
import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public Integer insertUserInfo(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }

    @Override
    public Integer updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateById(userInfo);
    }

    @Override
    public Integer deleteUserInfo(String authorId) {
        return userInfoMapper.deleteById(authorId);
    }

    @Override
    public UserInfo findUserInfoByAuthorId(String authorId) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("author_id", authorId);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        return userInfo;
    }

    /* ***************   基本方法   ******************  */

    public ServerResponse addUserInfo(UserInfoDTO userInfoDTO) {

        return null;
    }

    /**
     * @Description: 接口信息排掉了邮箱和手机号码.
     * @Author: Bernie_fang
     * @Since: 2021/9/13 21:14
     * @param userInfoDTO:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    public ServerResponse updateUserInfo(UserInfoDTO userInfoDTO) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO, userInfo);
        this.updateUserInfo(userInfo);
        return ServerResponse.success();
    }

    /**
     * @Description: 查询所有的用户信息
     * @Author: Bernie_fang
     * @Since: 2021/9/13 21:45
     * @param currentPage:
     * @param size:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    public ServerResponse queryUserInfo(Integer currentPage, Integer size) {
        Page<UserInfo> page = new Page<>(currentPage, size);
        userInfoMapper.selectPage(page, null);
        PagingData pagingData = new PagingData();
        pagingData.setTotal(page.getTotal());
        pagingData.setData(page.getRecords());
        return ServerResponse.success(pagingData);
    }

    @Override
    public UserInfo findUserInfoByEmail(String email) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        return userInfo;
    }
}
