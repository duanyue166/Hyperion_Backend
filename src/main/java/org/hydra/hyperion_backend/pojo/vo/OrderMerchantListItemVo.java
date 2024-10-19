package org.hydra.hyperion_backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * author： Shuowei Hou
 * date： 2024/10/18
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMerchantListItemVo {
    Integer orderId;
    String state;
    String userName;
    Date createTime;
    Date completeTime;
    String consignee;
    String address;
    List<GoodsItemVo> items;
}
