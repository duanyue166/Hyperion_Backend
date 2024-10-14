package org.hydra.hyperion_backend.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hydra.hyperion_backend.annotation.LocalTimeFormat;
import org.hydra.hyperion_backend.pojo.ConsumerInfo;
import org.hydra.hyperion_backend.pojo.MerchantInfo;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailVo {
    private Integer id; //订单ID
    private double payment; // 付款金额
    private String state;   //订单状态

    @LocalTimeFormat
    private Date createTime;    //下单时间

    @LocalTimeFormat
    private Date completeTime;  //完成时间
    
    private String coverUrl;    //封面图片

    private ConsumerInfo consumer;  //买家信息

    private MerchantInfo merchant;  //卖家信息

    private List<SoldGoodsDetailVo> goodsList; //商品列表
}
