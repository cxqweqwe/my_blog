package com.fang.backgroundapi.controller;


import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.BlogInfo;
import com.fang.backgroundapi.service.impl.LikesRecordServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

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
@Api(tags = "博客喜欢记录")
@Validated   // Get请求方法需要这个注解配合
public class LikesRecordController extends BaseController {

    @Autowired
    private LikesRecordServiceImpl recordService;

    @GetMapping("/checkLike")
    @ApiOperation(value = "获取博客信息", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse checkLike(@RequestParam("articleId") @NotBlank(message = "参数不能为空") String articleId,
                                    @RequestParam("authorId") @NotBlank(message = "参数不能为空") String authorId) {
        Boolean flag = recordService.checkLike(authorId, articleId);
        return ServerResponse.success(flag);
    }

}

