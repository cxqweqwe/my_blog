package com.fang.backgroundapi.mapper;

import com.fang.backgroundapi.pojo.DO.LabelRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 标签记录表 Mapper 接口
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Repository
public interface LabelRecordMapper extends BaseMapper<LabelRecord> {

    /**
     * @Description: 批量插入数据
     * @Author: Bernie_fang
     * @Since: 2021/9/26 16:09
     * @param labelRecordList:
     * @return: java.lang.Integer
     **/
    public Integer insertLabelRecord(@Param("labelRecordList") List<LabelRecord> labelRecordList);

    /**
     * @Description: 批量为对应的标签的使用次数直接加1
     * @Author: Bernie_fang
     * @Since: 2021/9/26 16:55
     * @param idList:
     * @return: java.lang.Integer
     **/
    public Integer updateLabelRecord(@Param("idList") List<Integer> idList);



}
