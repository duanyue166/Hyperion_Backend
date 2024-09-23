package org.hydra.hyperion_backend.pojo.entity;


import lombok.Data;

import java.util.Date;

@Data
public class Order {

    private long id;
    private long userId;
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


}
