package com.fang.backgroundapi.controller;


import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.service.impl.LikesRecordServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 点赞记录表，记录用户对博客文章的点赞 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/likesRecord")
public class LikesRecordController extends BaseController {

    // @Autowired
    // private LikesRecordServiceImpl recordService;

}

