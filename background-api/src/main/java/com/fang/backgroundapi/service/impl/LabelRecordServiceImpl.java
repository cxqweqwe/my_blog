package com.fang.backgroundapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.LabelClass;
import com.fang.backgroundapi.pojo.DO.LabelRecord;
import com.fang.backgroundapi.mapper.LabelRecordMapper;
import com.fang.backgroundapi.service.LabelRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 标签记录表 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Service
public class LabelRecordServiceImpl implements LabelRecordService {

    @Autowired
    private LabelRecordMapper labelRecordMapper;

    @Override
    public Integer insertLabelRecord(LabelRecord labelRecord) {
        return labelRecordMapper.insert(labelRecord);
    }

    @Override
    public Integer insertLabelRecord(List<LabelRecord> labelRecordList) {
        return labelRecordMapper.insertLabelRecord(labelRecordList);
    }

    @Override
    public Integer updateLabelRecord(List<Integer> idList) {
        return labelRecordMapper.updateLabelRecord(idList);
    }

    @Override
    public Integer updateLabelRecord(LabelRecord labelRecord) {
        return labelRecordMapper.updateById(labelRecord);
    }

    @Override
    public Integer deleteLabelRecord(Integer id) {
        return labelRecordMapper.deleteById(id);
    }

    @Override
    public LabelRecord findLabelRecord(Integer id) {
        return labelRecordMapper.selectById(id);
    }

    @Override
    public LabelRecord findLabelRecord(String labelName) {
        QueryWrapper<LabelRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("label_name", labelName);
        LabelRecord labelRecord = labelRecordMapper.selectOne(wrapper);
        return labelRecord;
    }

    @Override
    public PagingData queryLabelRecord(Integer currentPage, Integer size) {
        Page<LabelRecord> page = new Page<>(currentPage,size);
        QueryWrapper<LabelRecord> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("times");
        labelRecordMapper.selectPage(page,wrapper);
        PagingData pagingData = new PagingData();
        pagingData.setTotal(page.getTotal());
        pagingData.setData(page.getRecords());
        return pagingData;
    }

    @Transactional(propagation = Propagation.REQUIRED, value = "detTransactionManager",
            rollbackFor = {Exception.class, RuntimeException.class, MyException.class})
    public ServerResponse addLabelRecord(String labelNames) {
        ArrayList<LabelRecord> insertRecords = new ArrayList<>();
        ArrayList<Integer> updateRecords = new ArrayList<>();
        String[] split = labelNames.split(",");
        for (String laName : split) {
            LabelRecord labelRecord = this.findLabelRecord(laName);
            if (labelRecord != null) {
                //已经有的
                updateRecords.add(labelRecord.getId());
            } else {
                // 新增加入的
                LabelRecord record = new LabelRecord();
                record.setLabelName(laName);
                record.setLabelClass(-1);
                record.setCreateTime(new Date());
                record.setModifiedTime(new Date());
                insertRecords.add(record);
            }
        }
        labelRecordMapper.insertLabelRecord(insertRecords);
        labelRecordMapper.updateLabelRecord(updateRecords);
        return ServerResponse.success();
    }

}
