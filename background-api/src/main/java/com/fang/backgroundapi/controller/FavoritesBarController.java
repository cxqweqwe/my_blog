package com.fang.backgroundapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.FavoritesBar;
import com.fang.backgroundapi.pojo.DTO.FavoritesBarDTO;
import com.fang.backgroundapi.service.impl.FavoritesBarServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 收藏夹信息表 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/favoritesBar")
@Api(tags = "收藏夹信息接口")
@Validated   // Get请求方法需要这个注解配合
public class FavoritesBarController extends BaseController {

    @Autowired
    private FavoritesBarServiceImpl favoritesBarService;

    @PostMapping("/create")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "创建收藏夹", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse create(@RequestBody @Valid FavoritesBarDTO favoritesBarDTO) throws MyException {
        FavoritesBar favoritesBar = new FavoritesBar();
        BeanUtils.copyProperties(favoritesBarDTO, favoritesBar);
        favoritesBar.setAuthorId(super.getAuthorId());
        favoritesBarService.save(favoritesBar);
        return ServerResponse.success();
    }

    @PostMapping("/update")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "修改收藏夹", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse update(@RequestBody @Valid FavoritesBarDTO favoritesBarDTO) throws MyException {
        FavoritesBar favoritesBar = new FavoritesBar();
        BeanUtils.copyProperties(favoritesBarDTO, favoritesBar);
        favoritesBar.setAuthorId(super.getAuthorId());
        favoritesBarService.updateById(favoritesBar);
        return ServerResponse.success();
    }

    @GetMapping("/delete/{id}")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "删除收藏夹", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse delete(@PathVariable("id") String id) throws MyException {
        favoritesBarService.removeById(id);
        return ServerResponse.success();
    }


    @GetMapping("/query/{authorId}")
    @ApiOperation(value = "查询收藏夹", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse query(@PathVariable("authorId") String authorId) {
        QueryWrapper<FavoritesBar> wrapper = new QueryWrapper<>();
        wrapper.eq("author_id", authorId);
        List<FavoritesBar> favoritesBarList = favoritesBarService.list(wrapper);
        return ServerResponse.success(favoritesBarList);
    }


}

