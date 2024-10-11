package org.hydra.hyperion_backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoldGoodsDetailVo {
    Integer goodsId;
    Integer quantity;
    Integer merId;
    String coverUrl;
    double price;
    double discount;
}
