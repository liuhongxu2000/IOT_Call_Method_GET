package com.hv.iot.dto;

import lombok.Data;

/**
 * @program: newpowerplant-management
 * @description: 设备属性最新值DTO
 * @author: Dd7
 * @create: 2023-11-06 15:00
 **/

@Data
public class EquipmentFieldNewDTO {

    private Object data;
    private String code;
    private String message;
}
