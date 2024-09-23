package org.hydra.hyperion_backend.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    /**
     * 订单状态 1待付款 2已接单 3已完成 4已取消
     */
    public static final Integer PLACED = 1;
    public static final Integer CONFIRMED = 2;
    public static final Integer SHIPPED = 3;
    public static final Integer COMPLETE = 4;

    //订单号
    private int orderID;
    //卖家编号
    private int userID;
    //订单状态
    private int state;
    //实付款
    private double payment;
    //收货人
    private String consignee;
    //联系电话
    private String contact;
    //运输地址
    private int address;
    //创建时间
    private Timestamp createTime;
    //完成时间
    private Timestamp completeTime;
}
