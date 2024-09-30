package org.hydra.hyperion_backend.pojo.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoodsItemVo {
    private String name;
    private long totSales;
    private double price;
    private String coverUrl;
    private long posCount;
    private long negCount;
}
