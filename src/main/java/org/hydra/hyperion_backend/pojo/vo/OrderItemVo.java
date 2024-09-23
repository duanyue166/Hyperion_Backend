package org.hydra.hyperion_backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemVo {

    private String productName; // 商品名称
    private int quantity; // 购买数量
    private double price; // 单价
    private double totalPrice; // 总价 (计算得出: price * quantity)
}
