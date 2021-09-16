package com.fang.backgroundapi.controller;

import com.fang.backgroundapi.pojo.VO.TestVO;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bernie_fang
 * @create 2021/5/24 11:46
 **/
@RestController
public class TestController extends BaseController {

    @GetMapping("/test")
    // @RequiresRoles(value = "test",logical = Logical.OR)
    public List<TestVO> test() {
        // TestVO testVO = new TestVO();
        // testVO.setHrefLink("blog-single.html");
        // testVO.setAuthorImg("");
        // testVO.setTitle("测试标题");
        // testVO.setAuthorName("testNam");
        // testVO.setArticleLink("文章标题");
        // testVO.setIntroduction("An Incredibly Easy Method That Works For All");
        // testVO.setTime("29 May 2021");
        ArrayList<TestVO> testVOList = new ArrayList<>();
        // testVOList.add(testVO);
        // testVOList.add(testVO);
        // testVOList.add(testVO);
        String token = super.getToken();
        System.out.println(token);
        return testVOList;
    }

    @GetMapping("/test2")
    @RequiresRoles(value = "test2",logical = Logical.OR)
    public List<TestVO> test2() {
        TestVO testVO = new TestVO();
        testVO.setHrefLink("blog-single.html");
        testVO.setAuthorImg("");
        testVO.setTitle("测试标题");
        testVO.setAuthorName("testNam");
        testVO.setArticleLink("文章标题");
        testVO.setIntroduction("An Incredibly Easy Method That Works For All");
        testVO.setTime("29 May 2021");
        ArrayList<TestVO> testVOList = new ArrayList<>();
        testVOList.add(testVO);
        testVOList.add(testVO);
        testVOList.add(testVO);
        return testVOList;
    }

}
