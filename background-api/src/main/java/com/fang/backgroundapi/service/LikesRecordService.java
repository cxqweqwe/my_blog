package com.fang.backgroundapi.service;

import com.fang.backgroundapi.pojo.DO.BlogInfo;
import com.fang.backgroundapi.pojo.DO.LikesRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 点赞记录表，记录用户对博客文章的点赞 服务类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
public interface LikesRecordService {

    public Integer recodeLikeBlog(String authorId, String articleId);

    public Integer deleteRecode(String id);

    public LikesRecord findLikeRecord(String authorId, String articleId);



}
