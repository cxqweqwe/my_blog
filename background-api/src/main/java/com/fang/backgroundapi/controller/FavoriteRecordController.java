package com.fang.backgroundapi.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.FavoriteRecord;
import com.fang.backgroundapi.pojo.DTO.FavoriteRecordDTO;
import com.fang.backgroundapi.pojo.DTO.LoginDTO;
import com.fang.backgroundapi.service.impl.FavoriteRecordServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;

/**
 * <p>
 * 收藏记录表 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2022-02-21
 */
@RestController
@RequestMapping("/favoriteRecord")
@Api(tags = "收藏记录接口")
@Validated   // Get请求方法需要这个注解配合
public class FavoriteRecordController extends BaseController {

    @Autowired
    private FavoriteRecordServiceImpl favoriteRecordService;

    @PostMapping("/collect")
    @ApiOperation(value = "收藏", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse collect(@RequestBody @Valid FavoriteRecordDTO favoriteRecordDTO) throws MyException {
        FavoriteRecord record = new FavoriteRecord();
        BeanUtils.copyProperties(favoriteRecordDTO, record);
        record.setAuthorId(super.getAuthorId());
        favoriteRecordService.save(record);
        return ServerResponse.success();
    }

    @GetMapping("/unCollect/{id}")
    @ApiOperation(value = "取消收藏", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse unCollect(@PathVariable("id") String id) throws MyException {
        UpdateWrapper<FavoriteRecord> wrapper = new UpdateWrapper<>();
        wrapper.set("deleted", 1)
                .eq("id", id)
                .eq("author_id", super.getAuthorId());
        favoriteRecordService.update(wrapper);
        return ServerResponse.success();
    }

    @GetMapping("/query")
    @ApiOperation(value = "查找收藏", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse query(@RequestParam(value = "authorId", required = false) String authorId,
                                @RequestParam(value = "favoritesId", required = false) String favoritesId) {
        QueryWrapper<FavoriteRecord> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(authorId), "author_id", authorId)
                .eq(StringUtils.isNotEmpty(favoritesId), "favorites_id", favoritesId)
                .orderByDesc("create_time");
        List<FavoriteRecord> list = favoriteRecordService.list(wrapper);
        return ServerResponse.success(list);
    }

}

