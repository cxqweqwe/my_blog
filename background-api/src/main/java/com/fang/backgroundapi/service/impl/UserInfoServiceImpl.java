package com.fang.backgroundapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fang.backgroundapi.common.CommonInfo;
import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.pojo.DO.UserInfo;
import com.fang.backgroundapi.mapper.UserInfoMapper;
import com.fang.backgroundapi.pojo.DTO.UserInfoDTO;
import com.fang.backgroundapi.pojo.VO.BloggerVO;
import com.fang.backgroundapi.service.UserInfoService;
import com.fang.backgroundapi.typehandler.Encrypt;
import com.fang.backgroundapi.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.poifs.crypt.Encryptor;
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

    @Autowired
    private RedisUtils redisUtils;

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

    /**
     * Description: 查询所有用户信息
     * @Author: Bernie_fang
     * @Since: 2022/3/2 22:29
     * @param deleted:
     * @return: java.util.List<com.fang.backgroundapi.pojo.DO.UserInfo>
     **/
    public List<UserInfo> queryUserInfo(Integer deleted) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted", deleted);
        List<UserInfo> userInfos = userInfoMapper.selectList(wrapper);
        return userInfos;
    }

    @Override
    public UserInfo findUserInfoByEmail(Encrypt email) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        return userInfo;
    }

    @Override
    public UserInfo findUserInfoByPhone(String phone) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("phone_number", phone);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        return userInfo;
    }

    public ServerResponse updatePhone(String phone, String verificationCode, String authorId) {
        UserInfo info = this.findUserInfoByPhone(phone);
        if (info != null){
            return ServerResponse.error(400, "同个手机号码不允许绑定多个账号", null);
        }
        Object o = redisUtils.get(CommonInfo.PHONE_RECENT_REQUEST + phone);// 获取手机对应的验证码
        String code = String.valueOf(o);
        if (StringUtils.isEmpty(code)){
            return ServerResponse.error(400, "验证码不存在", null);
        }
        if (!code.equals(verificationCode)){
            return ServerResponse.error(400, "验证码正确", null);
        }
        // 修改
        UpdateWrapper<UserInfo> wrapper = new UpdateWrapper<>();
        wrapper.set("phone_number", phone)
                .eq("author_id", authorId);
        userInfoMapper.update(null, wrapper);
        redisUtils.setRemove(CommonInfo.PHONE_RECENT_REQUEST + phone);//移除验证码
        StringBuffer stringBuffer = new StringBuffer(phone.substring(0, 3));
        stringBuffer.append("****").append(phone.substring(7));
        return ServerResponse.success(stringBuffer.toString());
    }

    public ServerResponse updateEmail(String email,String verificationCode,String authorId) {
        Encrypt encryptEmail = new Encrypt(email);
        UserInfo info = this.findUserInfoByEmail(encryptEmail);
        if (info != null){
            return ServerResponse.error(400, "同个邮箱不允许绑定多个账号", null);
        }
        Object o = redisUtils.get(CommonInfo.EMAIL_CODE + email);// 获取邮箱对应的验证码
        String code = String.valueOf(o);
        if (StringUtils.isEmpty(code)){
            return ServerResponse.error(400, "验证码不存在", null);
        }
        if (!code.equals(verificationCode)){
            return ServerResponse.error(400, "验证码不正确", null);
        }
        // 修改
        UpdateWrapper<UserInfo> wrapper = new UpdateWrapper<>();
        wrapper.set("email", encryptEmail)
                .eq("author_id", authorId);
        userInfoMapper.update(null, wrapper);
        redisUtils.setRemove(CommonInfo.PHONE_RECENT_REQUEST + email);//移除验证码
        int index = email.lastIndexOf("@") - 1;
        StringBuffer stringBuffer = new StringBuffer(email.substring(0, 2));
        stringBuffer.append("****").append(email.substring(index));
        return ServerResponse.success(stringBuffer.toString());
    }

    public ServerResponse updateAvatar(String avatar, String authorId) {
        UpdateWrapper<UserInfo> wrapper = new UpdateWrapper<>();
        wrapper.set("avatar_path", avatar)
                .eq("author_id", authorId);
        userInfoMapper.update(null, wrapper);
        return ServerResponse.success();
    }

    /**
     * Description: 获取blogger信息
     * @Author: Bernie_fang
     * @Since: 2022/1/30 23:08
     * @param authorId:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    public ServerResponse getBlogger(String authorId) {
        BloggerVO bloggerVO = userInfoMapper.gerBlogger(authorId);
        return ServerResponse.success(bloggerVO);
    }

}
