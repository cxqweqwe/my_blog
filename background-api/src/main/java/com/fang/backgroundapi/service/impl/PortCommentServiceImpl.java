package com.fang.backgroundapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.pojo.DO.PortComment;
import com.fang.backgroundapi.mapper.PortCommentMapper;
import com.fang.backgroundapi.pojo.VO.PortCommentVO;
import com.fang.backgroundapi.service.PortCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 论坛评论表 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Service
public class PortCommentServiceImpl extends ServiceImpl<PortCommentMapper, PortComment> implements PortCommentService {

    @Autowired
    private PortCommentMapper portCommentMapper;

    public PagingData queryPortComment(String portId, Integer curPage, Integer size) {
        curPage = (curPage - 1) * size;
        List<PortCommentVO> commentList = portCommentMapper.queryPortComment(portId, curPage, size);
        Long total = portCommentMapper.CommentCount(portId);
        return new PagingData(total, commentList);
    }

    /**
     * Description: 修改状态
     * @Author: Bernie_fang
     * @Since: 2022/2/23 15:20
     * @param id:
     * @param handleResult:  2表示代审核
     * @return: void
     **/
    public void handleReport(String id, Integer handleResult) {
        UpdateWrapper<PortComment> wrapper = new UpdateWrapper<>();
        wrapper.set("deleted", handleResult)
                .eq("id", id);
        portCommentMapper.update(null, wrapper);
    }

}
