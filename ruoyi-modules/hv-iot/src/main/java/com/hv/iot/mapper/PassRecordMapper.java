package com.hv.iot.mapper;

import com.hv.iot.domain.PassRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 过点记录 Mapper 接口
 * </p>
 *
 * @author liuhongxu2000
 * @since 2024-03-14
 */
@Mapper
public interface PassRecordMapper extends BaseMapper<PassRecord> {

    /**
     * 判断flag标记后回传vin
     * @param vin
     */
    void  InsertVin(String vin);
    void  InsertDate(String date);
    void InsertPassRecord(PassRecord passRecord);

}
