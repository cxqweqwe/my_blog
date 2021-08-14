package com.fang.backgroundapi.service.impl;

import com.fang.backgroundapi.pojo.DO.LikesRecord;
import com.fang.backgroundapi.mapper.LikesRecordMapper;
import com.fang.backgroundapi.service.LikesRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 点赞记录表，记录用户对博客文章的点赞 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Service
public class LikesRecordServiceImpl extends ServiceImpl<LikesRecordMapper, LikesRecord> implements LikesRecordService {

}
