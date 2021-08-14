package com.fang.backgroundapi.service.impl;

import com.fang.backgroundapi.pojo.DO.PostInfo;
import com.fang.backgroundapi.mapper.PostInfoMapper;
import com.fang.backgroundapi.service.PostInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 论坛的帖子信息 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Service
public class PostInfoServiceImpl extends ServiceImpl<PostInfoMapper, PostInfo> implements PostInfoService {

}
