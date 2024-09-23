package org.hydra.hyperion_backend.pojo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoldGoods {
    private int goodsID;
    private int OrderID;
    private int quality;
    private double unitPrice;
    private int review;
}
