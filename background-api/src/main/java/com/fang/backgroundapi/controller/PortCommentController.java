package com.fang.backgroundapi.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.PortComment;
import com.fang.backgroundapi.pojo.DTO.ArticleDTO;
import com.fang.backgroundapi.pojo.DTO.PortCommentDTO;
import com.fang.backgroundapi.service.impl.PortCommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 论坛评论表 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/portComment")
@Validated   // Get请求方法需要这个注解配合
@Api(tags = "论帖相关接口")
public class PortCommentController extends BaseController {

    @Autowired
    private PortCommentServiceImpl portCommentService;

    @PostMapping("/release")
    @ApiOperation(value = "论贴评论发布", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse releasePortComment(@RequestBody @Valid PortCommentDTO portCommentDTO) throws MyException {
        PortComment comment = new PortComment();
        BeanUtils.copyProperties(portCommentDTO, comment);
        comment.setAuthorId(super.getAuthorId());
        portCommentService.save(comment);
        return ServerResponse.success();
    }

    @GetMapping("/query")
    @ApiOperation(value = "论贴评论查询", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse queryPortComment(@RequestParam("portId") String portId,
                                           @RequestParam("curPage") Integer curPage,
                                           @RequestParam("size") Integer size) throws MyException {
        PagingData pagingData = portCommentService.queryPortComment(portId, curPage, size);
        return ServerResponse.success(pagingData);
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "论贴评论删除", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse deletePortComment(@PathVariable("id") String id) throws MyException {
        UpdateWrapper<PortComment> wrapper = new UpdateWrapper<>();
        wrapper.set("deleted", 1)
                .eq("id", id)
                .eq("author_id", super.getAuthorId());
        portCommentService.update(wrapper);
        return ServerResponse.success();
    }

    @GetMapping("/report/{id}")
    @ApiOperation(value = "论贴评论举报", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse reportPortComment(@PathVariable("id") String id) {
        UpdateWrapper<PortComment> wrapper = new UpdateWrapper<>();
        wrapper.set("state", 2).eq("id", id);
        portCommentService.update(wrapper);
        return ServerResponse.success();
    }


}

