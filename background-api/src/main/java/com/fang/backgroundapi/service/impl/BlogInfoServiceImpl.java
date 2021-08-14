package com.fang.backgroundapi.service.impl;

import com.fang.backgroundapi.pojo.DO.BlogInfo;
import com.fang.backgroundapi.mapper.BlogInfoMapper;
import com.fang.backgroundapi.service.BlogInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客信息统计表 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Service
public class BlogInfoServiceImpl extends ServiceImpl<BlogInfoMapper, BlogInfo> implements BlogInfoService {

}
