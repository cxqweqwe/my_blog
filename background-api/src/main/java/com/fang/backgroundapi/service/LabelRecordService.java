package com.fang.backgroundapi.service;

import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.pojo.DO.LabelRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 标签记录表 服务类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
public interface LabelRecordService {

    public Integer insertLabelRecord(LabelRecord labelRecord);

    public Integer insertLabelRecord(List<LabelRecord> labelRecordList);

    public Integer updateLabelRecord(LabelRecord labelRecord);

    /**
     * @Description: 批量为对应的标签的使用次数直接加1
     * @Author: Bernie_fang
     * @Since: 2021/9/26 16:55
     * @param idList:
     * @return: java.lang.Integer
     **/
    public Integer updateLabelRecord(@Param("idList") List<Integer> idList);

    public Integer deleteLabelRecord(Integer id);

    public LabelRecord findLabelRecord(Integer id);

    public LabelRecord findLabelRecord(String labelName);

    public PagingData queryLabelRecord(Integer currentPage, Integer size);

}
