package com.hv.iot.service.impl;

import com.hv.iot.domain.PassRecord;
import com.hv.iot.mapper.PassRecordMapper;
import com.hv.iot.service.IPassRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * <p>
 * 过点记录 服务实现类
 * </p>
 *
 * @author liuhongxu2000
 * @since 2024-03-14
 */
@Service
public class PassRecordServiceImpl extends ServiceImpl<PassRecordMapper, PassRecord> implements IPassRecordService {

    @Autowired
    PassRecordMapper passRecordMapper;
    @Override
    public void InsertVin(String vin) {
        passRecordMapper.InsertVin(vin);
    }
    @Override
    public void InsertDate(String date) {
        passRecordMapper.InsertDate(date);
    }

    @Override
    public void InsertPassRecord(PassRecord passRecord) {
        passRecordMapper.InsertPassRecord(passRecord);
    }


}
