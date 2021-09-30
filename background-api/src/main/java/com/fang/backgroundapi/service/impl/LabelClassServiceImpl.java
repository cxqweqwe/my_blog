package com.fang.backgroundapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.pojo.DO.LabelClass;
import com.fang.backgroundapi.mapper.LabelClassMapper;
import com.fang.backgroundapi.service.LabelClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.javassist.CtNewConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 标签分类表，方便管理的 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Service
public class LabelClassServiceImpl implements LabelClassService {

    @Autowired
    private LabelClassMapper labelClassMapper;

    @Override
    public Integer insertLabelClass(LabelClass labelClass) {
        return labelClassMapper.insert(labelClass);
    }

    @Override
    public Integer updateLabelClass(LabelClass labelClass) {
        return labelClassMapper.updateById(labelClass);
    }

    @Override
    public Integer deleteLabelClass(Integer id) {
        return labelClassMapper.deleteById(id);
    }

    @Override
    public LabelClass findLabelClass(Integer id) {
        LabelClass labelClass = labelClassMapper.selectById(id);
        return labelClass;
    }

    @Override
    public LabelClass findLabelClass(String className) {
        QueryWrapper<LabelClass> wrapper = new QueryWrapper<>();
        wrapper.eq("class_name", className);
        LabelClass labelClass = labelClassMapper.selectOne(wrapper);
        return labelClass;
    }

    @Override
    public List<LabelClass> queryLabelClass() {
        return labelClassMapper.selectList(null);
    }


    public ServerResponse addLabelClass(String className) {
        LabelClass labelClass = this.findLabelClass(className);
        if (labelClass != null) {
            return ServerResponse.success("该标签已经添加");
        }
        LabelClass newLabel = new LabelClass();
        newLabel.setClassName(className);
        this.insertLabelClass(newLabel);
        return ServerResponse.success();
    }

}
