package com.fang.backgroundapi.controller;


import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.PostInfo;
import com.fang.backgroundapi.pojo.DTO.ArticleDTO;
import com.fang.backgroundapi.pojo.DTO.PostInfoDTO;
import com.fang.backgroundapi.pojo.VO.ArticleVO;
import com.fang.backgroundapi.service.impl.PostInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 论坛的帖子信息 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/postInfo")
@Api(tags = "论坛帖子")
@Slf4j
public class PostInfoController extends BaseController {

    @Autowired
    private PostInfoServiceImpl postInfoService;

    @PostMapping("/release")
    @ApiOperation(value = "博客发布", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse releaseArticle(@RequestBody @Valid PostInfoDTO postInfoDTO) throws MyException {
        PostInfo info = new PostInfo();
        BeanUtils.copyProperties(postInfoDTO, info);
        info.setAuthorId(super.getAuthorId());
        postInfoService.save(info);
        return ServerResponse.success();
    }

    @GetMapping("/query")
    @ApiOperation(value = "获取博客详情", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse articleDetail() {

        return ServerResponse.success();
    }

}

