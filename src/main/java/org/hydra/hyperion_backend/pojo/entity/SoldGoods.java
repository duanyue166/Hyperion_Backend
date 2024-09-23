package org.hydra.hyperion_backend.pojo.entity;


import lombok.Data;

@Data
public class SoldGoods {

    private long orderId;
    private long goodsId;
    private long quantity;
    private double unitPrice;
    private long review;

}
