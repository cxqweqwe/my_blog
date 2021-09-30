package com.fang.backgroundapi.controller;


import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.pojo.DO.LabelRecord;
import com.fang.backgroundapi.pojo.VO.MostPopularInfoVO;
import com.fang.backgroundapi.service.impl.LabelRecordServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 标签记录表 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/labelRecord")
@Api(tags = "标签相关接口")
@Validated   // Get请求方法需要这个注解配合
public class LabelRecordController {

    @Autowired
    private LabelRecordServiceImpl recordService;

    /**
     * @Description: 查询所有的标签
     * @Author: Bernie_fang
     * @Since: 2021/9/12 22:10
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    @GetMapping("/query")
    @ApiOperation(value = "查询所有的标签", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse queryRecent(@RequestParam @ApiParam("参数必须为数字") Integer currentPage,
                                      @RequestParam @ApiParam("参数必须为数字") Integer size) {
        PagingData pagingData = recordService.queryLabelRecord(currentPage, size);
        return ServerResponse.success(pagingData);
    }



}

