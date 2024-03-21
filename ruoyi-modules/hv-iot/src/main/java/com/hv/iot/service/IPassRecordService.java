package com.hv.iot.service;

import com.hv.iot.domain.PassRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 过点记录 服务类
 * </p>
 *
 * @author liuhongxu2000
 * @since 2024-03-14
 */
public interface IPassRecordService extends IService<PassRecord> {

    /**
     * service
     * @param vin
     */
    void InsertVin(String vin);
    void InsertDate(String date);
    void InsertPassRecord(PassRecord passRecord);
}
