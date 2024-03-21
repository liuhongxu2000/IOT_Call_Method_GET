package com.hv.iot.listener;

import com.hv.iot.domain.PassRecord;
import com.hv.iot.dto.EquipmentFieldNewDTO;
import com.hv.iot.service.IPassRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author liuhongxu2000
 * @create 2024/3/21 19:11
 */
@Component
@Slf4j
public class IotListener implements ApplicationRunner {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    IPassRecordService iPassRecordService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i <99 ; i++) {
            Thread.sleep(10000);
            forrun();
        }

    }
    
    public  void   forrun(){
        String uri = String.format("http://172.66.88.247:9700/equipment/shan_test/field-by-code/VIN");
        try {
            ResponseEntity<EquipmentFieldNewDTO> response = restTemplate.getForEntity(uri, EquipmentFieldNewDTO.class);
            EquipmentFieldNewDTO equipmentFieldNewDTO=response.getBody();
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println("DATE----------"+dateTime.format(formatter));
            log.info("ALL--------"+String.valueOf(equipmentFieldNewDTO));
            PassRecord items = new PassRecord();
            items.setVin((String) equipmentFieldNewDTO.getData());
            items.setDate(dateTime.format(formatter));
            iPassRecordService.InsertPassRecord(items);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
