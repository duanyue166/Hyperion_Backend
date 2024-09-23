package org.hydra.hyperion_backend.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDetailVo {
    private String name;//商品名称
    private String coverUrl;//图片
    private String desc;//描述
    private double price;//价格
}
