package com.fang.backgroundapi.controller;


import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.PostInfo;
import com.fang.backgroundapi.pojo.DTO.PostInfoDTO;
import com.fang.backgroundapi.pojo.VO.PostInfoVO;
import com.fang.backgroundapi.service.impl.PostInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "论坛新帖发布", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse releasePostInfo(@RequestBody @Valid PostInfoDTO postInfoDTO) throws MyException {
        PostInfo info = new PostInfo();
        BeanUtils.copyProperties(postInfoDTO, info);
        info.setAuthorId(super.getAuthorId());
        postInfoService.save(info);
        return ServerResponse.success();
    }

    @PostMapping("/update")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "论坛新帖发布", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse updatePostInfo(@RequestBody @Valid PostInfoDTO postInfoDTO) throws MyException {
        PostInfo info = new PostInfo();
        BeanUtils.copyProperties(postInfoDTO, info);
        info.setAuthorId(super.getAuthorId());
        postInfoService.updateById(info);
        return ServerResponse.success();
    }

    @GetMapping("/query")
    @ApiOperation(value = "帖子查询", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse queryPostInfo(@RequestParam("curPage") Integer curPage,
                                        @RequestParam("size") Integer size,
                                        @RequestParam(name = "keywords", required = false) String keywords) {
        PagingData data = postInfoService.queryPostInfo(curPage, size, keywords);
        return ServerResponse.success(data);
    }

    @GetMapping("/get")
    @ApiOperation(value = "帖子查询", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse findPostInfo(@RequestParam("postId") String postId) {
        PostInfoVO postInfo = postInfoService.findPostInfo(postId);
        return ServerResponse.success(postInfo);
    }

    @GetMapping("/delete/{postId}")
    @ApiOperation(value = "帖子查询", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse deletePostInfo(@PathVariable("postId") String postId) {
        postInfoService.removeById(postId);
        return ServerResponse.success();
    }

    @GetMapping("/personal")
    @ApiOperation(value = "帖子查询", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse findPersonalPostInfo(@RequestParam("authorId") String authorId,
                                               @RequestParam("curPage") Integer curPage,
                                               @RequestParam("size") Integer size) {
        PagingData data = postInfoService.findPersonalPostInfo(authorId, curPage, size);
        return ServerResponse.success(data);
    }

}

