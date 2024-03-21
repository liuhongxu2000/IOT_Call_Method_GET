package com.hv.iot.consumer;

import com.hv.iot.service.IPassRecordService;
import com.hvisions.common.vo.ResultVO;
import com.hvisions.edge.client.setting.EquipmentClient;
import com.hvisions.edge.equipment.EquipmentData;
import com.hvisions.edge.equipment.EquipmentService;
import com.hvisions.edge.equipment.HttpBuilderInstance;
import com.hvisions.iot.common.access.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuhongxu2000
 */
@Component
public class IotConsumer implements EquipmentService.FieldConsumer {

//    @Autowired
//    private HttpBuilderInstance httpBuilder;
//    @Autowired
//    IPassRecordService iPassRecordService;

    @Override
    public void accept(EquipmentData equipmentInfo, String fieldName, Object value) {
        System.out.println("value = " + value);
//        Boolean flag = (Boolean) value;
//        if (flag){
//            Write();
//        }else{
//            System.out.println("flag is false");
//        }
    }

}