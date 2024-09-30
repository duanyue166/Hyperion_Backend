package org.hydra.hyperion_backend.pojo.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.hydra.hyperion_backend.annotation.ValidState;
import org.hydra.hyperion_backend.pojo.entity.Goods;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsRequest {
    /**
     * 商品分类
     */
    @Length(max = 10)
    private String category;
    /**
     * 封面图片url
     */
    @URL
    private String coverUrl;
    /**
     * 商品描述
     */
    private String desc;
    /**
     * 折扣
     */
    @DecimalMin("0.0")
    @DecimalMax("1.0")
    private Double discount;
    /**
     * 商品名称
     */
    @NotNull
    private String name;
    /**
     * 价格
     */
    @NotNull
    @Min(0)
    private double price;
    /**
     * 库存数量
     */
    @NotNull
    @Min(0)
    private long quantity;
    /**
     * 商品的卖家编号
     */
    private long userId;

    @NotNull(groups = {Update.class})
    private long id;

    public interface Update {
    }

    public GoodsRequest(Goods goods) {
        this.category = goods.getCategory();
        this.coverUrl = goods.getCoverUrl();
        this.desc = goods.getDesc();
        this.discount = goods.getDiscount();
        this.name = goods.getName();
        this.price = goods.getPrice();
        this.quantity = goods.getQuantity();
        this.userId = goods.getUserId();
        this.id = goods.getId();
    }
}
