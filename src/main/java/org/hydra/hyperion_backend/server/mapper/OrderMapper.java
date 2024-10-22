package org.hydra.hyperion_backend.server.mapper;

import org.apache.ibatis.annotations.*;
import org.hydra.hyperion_backend.pojo.entity.Order;
import org.hydra.hyperion_backend.pojo.vo.OrderDetailVo;
import org.hydra.hyperion_backend.pojo.vo.OrderListItemVo;
import org.hydra.hyperion_backend.pojo.vo.OrderMerchantListItemVo;
import org.hydra.hyperion_backend.pojo.vo.SoldGoodsDetailVo;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<SoldGoodsDetailVo> getTrolleyGoodsList(Integer userId, @Param("goodsIdList") List<Integer> goodsIdList);

    @Insert("insert into `order`(con_id, mer_id, state, payment, consignee, contact, address, create_time ,cover_url) " +
            "values(#{conId}, #{merId}, #{state}, #{payment}, #{consignee}, #{contact}, #{address}, now(),#{coverUrl});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addOrder(Order order);

    void addSoldGoods(Integer orderId, @Param("soldGoodsDetails") List<SoldGoodsDetailVo> soldGoodsDetails);

    void deleteTrolley(Integer userId, @Param("goodsIdList") List<Integer> goodsIdList);

    void updateState(Integer userId, Integer orderId, String state);

    List<OrderListItemVo> cList(int userId, String state);

    List<OrderMerchantListItemVo> mList(int userId, String state);

    void review(Integer orderId, Integer goodsId, Integer score);

    void decreaseQuantity(Integer orderId);

    void increaseTotSales(Integer orderId);

    OrderDetailVo detail(int userId, Integer orderId);

    @Select("select s.goods_id, g.name, s.quantity, o.mer_id, g.cover_url, s.unit_price as price, 1.0 as discount " +
            "from `order` o " +
            "   left join sold_goods s on o.id=s.order_id " +
            "   left join goods g on s.goods_id=g.id " +
            "where o.id = #{orderId}")
    List<SoldGoodsDetailVo> getSoldGoodsList(Integer orderId);

    @Select("select * " +
            "from `order` " +
            "where id = #{orderId}")
    Order getById(Integer orderId);
}
