package org.hydra.hyperion_backend.pojo.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoodsItemVo {
    private String coverUrl;
    private long id;
    private String name;
    private Integer number;
    private double singlePrice;
}
