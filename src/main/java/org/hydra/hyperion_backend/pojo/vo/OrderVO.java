package org.hydra.hyperion_backend.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hydra.hyperion_backend.pojo.entity.Order;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false) // 不使用父类属性
public class OrderVO  extends Order {
    private String details;

}
