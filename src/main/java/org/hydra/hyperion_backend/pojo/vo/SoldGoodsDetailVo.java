package org.hydra.hyperion_backend.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoldGoodsDetailVo {
    Integer goodsId;
    String name;
    Integer quantity;
    Integer merId;
    String coverUrl;
    double price;
    double discount;
}
