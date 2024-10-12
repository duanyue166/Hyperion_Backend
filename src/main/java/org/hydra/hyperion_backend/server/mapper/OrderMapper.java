package org.hydra.hyperion_backend.server.mapper;

import org.apache.ibatis.annotations.*;
import org.hydra.hyperion_backend.pojo.entity.Order;
import org.hydra.hyperion_backend.pojo.vo.OrderListItemVo;
import org.hydra.hyperion_backend.pojo.vo.SoldGoodsDetailVo;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<SoldGoodsDetailVo> getSoldGoodsDetails(Integer userId, @Param("goodsIdList") List<Integer> goodsIdList);

    @Insert("insert into `order`(con_id, mer_id, state, payment, consignee, contact, address, create_time ,cover_url) " +
            "values(#{conId}, #{merId}, #{state}, #{payment}, #{consignee}, #{contact}, #{address}, now(),#{coverUrl});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addOrder(Order order);

    void addSoldGoods(Integer orderId, @Param("soldGoodsDetails") List<SoldGoodsDetailVo> soldGoodsDetails);

    void deleteTrolley(Integer userId, @Param("goodsIdList") List<Integer> goodsIdList);

    void updateState(Integer userId, Integer orderId, String state);

    List<OrderListItemVo> cList(int userId, String state);

    List<OrderListItemVo> mList(int userId, String state);

    void review(Integer orderId, Integer goodsId, Integer score);
}
