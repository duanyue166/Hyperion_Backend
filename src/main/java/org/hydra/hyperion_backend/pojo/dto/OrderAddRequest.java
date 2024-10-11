package org.hydra.hyperion_backend.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddRequest {
    Integer addrId;
    List<Integer> goodsIdList;
}
