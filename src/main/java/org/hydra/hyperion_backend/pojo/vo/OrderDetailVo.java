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

    private String consignee; // 收货人姓名
    private String address; // 收货地址
    private String contact; // 联系电话
    private double payment; // 付款金额
    private String orderStatus; // 订单状态描述 (例如：待付款、已发货等)
}
