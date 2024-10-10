package org.hydra.hyperion_backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressListItemVo {
    Integer id;
    String district;
    String detail;
    String consignee;
    String contact;
    Integer isDefault;
}
