package com.fang.backgroundapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.pojo.DO.PostInfo;
import com.fang.backgroundapi.mapper.PostInfoMapper;
import com.fang.backgroundapi.pojo.VO.PostInfoVO;
import com.fang.backgroundapi.service.PostInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private PostInfoMapper postInfoMapper;

    public PagingData queryPostInfo(Integer curPage, Integer size, String keyWorks) {
        curPage = (curPage -1) * size;
        if (StringUtils.isNotBlank(keyWorks.trim())) {
            String[] split = keyWorks.trim().split("\\s+");
            List<String> keyWorksList = Arrays.asList(split);
            List<PostInfoVO> postInfoVOList = postInfoMapper.queryPostInfo(curPage, size, keyWorksList);
            Long total = postInfoMapper.countPostInfo(0, 1, keyWorksList, null, null);
            return new PagingData(total, postInfoVOList);
        }else {
            List<PostInfoVO> postInfoVOList = postInfoMapper.queryPostInfo(curPage, size, null);
            Long total = postInfoMapper.countPostInfo(0, 1, null, null, null);
            return new PagingData(total, postInfoVOList);
        }
    }

    public PostInfoVO findPostInfo(String postId) {
        return postInfoMapper.findPostInfo(postId);
    }

    public PagingData findPersonalPostInfo(String authorId, Integer curPage, Integer size) {
        curPage = (curPage -1) * size;
        List<PostInfoVO> postInfoVOList = postInfoMapper.selectPostInfo(curPage, size, null, authorId);
        Long total = postInfoMapper.countPostInfo(0, 1, null, null, authorId);
        return new PagingData(total, postInfoVOList);
    }

    /**
     * Description: 修改状态
     * @Author: Bernie_fang
     * @Since: 2022/2/23 15:20
     * @param postId:
     * @param handleResult:  2表示代审核
     * @return: void
     **/
    public void handleReport(String postId, Integer handleResult) {
        UpdateWrapper<PostInfo> wrapper = new UpdateWrapper<>();
        wrapper.set("deleted", handleResult)
                .eq("post_id", postId);
        postInfoMapper.update(null, wrapper);
    }

}
