package org.hydra.hyperion_backend.pojo.entity;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Order {
    private long id;
    private long conId;
    private long merId;
    /*
    *   订单状态 : PLACED CONFIRMED SHIPPED COMPLETE
     */
    private String state;
    private double payment;
    private String consignee;
    private String contact;
    private String address;
    private Date createTime;
    private Date completeTime;
    private String coverUrl;
}
