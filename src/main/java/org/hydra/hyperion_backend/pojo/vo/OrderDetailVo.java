package org.hydra.hyperion_backend.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailVo {
    private String productName; // 商品名称
    private int quantity; // 购买数量
    private double unitPrice; // 单价
    private double discount; // 折扣
    private double payment; // 付款金额
    private String consignee; // 收货人姓名
    private String address; // 收货地址
    private String contact; // 联系电话
    private String orderStatus; // 订单状态描述
}
