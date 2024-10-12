package org.hydra.hyperion_backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hydra.hyperion_backend.annotation.LocalTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListItemVo {
    Integer id;
    String state;
    double payment;
    @LocalTimeFormat
    Date createTime;
    String coverUrl;
}
