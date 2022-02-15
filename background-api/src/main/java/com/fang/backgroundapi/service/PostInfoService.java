package com.fang.backgroundapi.service;

import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.pojo.DO.PostInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fang.backgroundapi.pojo.VO.PostInfoVO;

/**
 * <p>
 * 论坛的帖子信息 服务类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
public interface PostInfoService extends IService<PostInfo> {

    public PagingData queryPostInfo(Integer curPage, Integer size, String keyWorks);

    public PostInfoVO findPostInfo(String postId);

}
