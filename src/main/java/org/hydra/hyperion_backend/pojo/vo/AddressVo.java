package org.hydra.hyperion_backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressVo {
    Integer Id;
    Integer provId;
    String provName;
    Integer cityId;
    String cityName;
    Integer distId;
    String distName;
    String detail;
    Integer isDefault;
}
