package org.hydra.hyperion_backend.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressAddRequest {
    /**
     * 城市代码
     */
    private Integer cityId;
    /**
     * 详细地址
     */
    private String detail;
    /**
     * 区县代码
     */
    private Integer distId;
    /**
     * 省份代码
     */
    private Integer provId;
    /**
     * 用户 ID
     */
    private Integer userId;
}
