package com.fang.backgroundapi.service;

import com.fang.backgroundapi.pojo.DO.LabelClass;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 标签分类表，方便管理的 服务类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
public interface LabelClassService {

    public Integer insertLabelClass(LabelClass labelClass);

    public Integer updateLabelClass(LabelClass labelClass);

    public Integer deleteLabelClass(Integer id);

    /**
     * @param id:
     * @Description: 根据id查找对应的标签分类
     * @Author: Bernie_fang
     * @Since: 2021/9/26 11:22
     * @return: java.lang.Integer
     **/
    public LabelClass findLabelClass(Integer id);

    /**
     * @param className:
     * @Description: 根据标签名字查找对应的标签分类
     * @Author: Bernie_fang
     * @Since: 2021/9/26 11:22
     * @return: java.lang.Integer
     **/
    public LabelClass findLabelClass(String className);

    /**
     * @Description: 查找全部
     * @Author: Bernie_fang
     * @Since: 2021/9/26 11:24

     * @return: java.util.List<com.fang.backgroundapi.pojo.DO.LabelClass>
     **/
    public List<LabelClass> queryLabelClass();

}
