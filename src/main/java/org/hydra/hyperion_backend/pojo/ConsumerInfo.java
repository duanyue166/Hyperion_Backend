package org.hydra.hyperion_backend.pojo;

import lombok.Data;

@Data
public class ConsumerInfo {
    private Integer conId; //买家ID
    private String consignee; // 收货人姓名
    private String address; // 收货地址
    private String contact; // 联系电话
}