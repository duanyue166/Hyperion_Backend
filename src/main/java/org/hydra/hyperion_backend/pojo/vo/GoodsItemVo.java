package org.hydra.hyperion_backend.pojo.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoodsItemVo {
    /**
     * 封面图片url
     */
    private String coverUrl;
    /**
     * 折扣
     */
    private Double discount;
    /**
     * 商品唯一编号
     */
    private long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 价格
     */
    private double price;
}
