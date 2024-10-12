package org.hydra.hyperion_backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrolleyItemVo {
    Integer id;
    Integer quantity;
    double price;
    double discount;
    String coverUrl;
    String name;
    String state;
}
