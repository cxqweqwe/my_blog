package com.fang.backgroundapi.controller;

import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.ReportInfo;
import com.fang.backgroundapi.pojo.DTO.ReportInfoDTO;
import com.fang.backgroundapi.service.impl.ArticleCommentServiceImpl;
import com.fang.backgroundapi.service.impl.ArticleServiceImpl;
import com.fang.backgroundapi.service.impl.PortCommentServiceImpl;
import com.fang.backgroundapi.service.impl.PostInfoServiceImpl;
import com.fang.backgroundapi.service.impl.ReportInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 举报信息表 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/reportInfo")
@Api(tags = "举报")
@Validated   // Get请求方法需要这个注解配合
public class ReportInfoController {

    @Autowired
    private ReportInfoServiceImpl reportInfoService;

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private ArticleCommentServiceImpl articleCommentService;

    @Autowired
    private PostInfoServiceImpl postInfoService;

    @Autowired
    private PortCommentServiceImpl portCommentService;

    @PostMapping("/report")
    @ApiOperation(value = "信息举报", response = ServerResponse.class, httpMethod = "POST")
    @Transactional(propagation = Propagation.REQUIRED, value = "detTransactionManager",
            rollbackFor = {Exception.class, RuntimeException.class, MyException.class})
    public ServerResponse reportReport(@RequestBody @Valid ReportInfoDTO reportInfoDTO) {
        ReportInfo reportInfo = new ReportInfo();
        BeanUtils.copyProperties(reportInfoDTO, reportInfo);
        reportInfoService.save(reportInfo);
        switch (reportInfo.getReportType()){
            case "0": articleService.handleReport(reportInfo.getReport(),2);
                break;
            case "1": postInfoService.handleReport(reportInfo.getReport(),2);
                break;
            case "2": articleCommentService.handleReport(reportInfo.getReport(),2);
                break;
            case "3": portCommentService.handleReport(reportInfo.getReport(),2);
                break;
            default: break;
        }
        return ServerResponse.success();
    }


}

