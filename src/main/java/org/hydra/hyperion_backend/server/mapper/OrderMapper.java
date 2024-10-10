package org.hydra.hyperion_backend.server.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("insert into sold_goods(order_id, goods_id, quantity, unit_price) " +
            "select null, t.goods_id, t.quantity, g.price * g.discount " +
            "from trolley t " +
            "   join goods g on t.goods_id = g.id " +
            "where t.user_id = #{userid} and t.goods_id in (#{goodsidlist});")
    void insertSoldGoods(Integer userId, List<Integer> goodsIdList,int a);

    @Insert("insert into sold_goods(order_id, goods_id, quantity, unit_price) " +
            "select o.id,   " +
            "from user u join trolley t on u.id=t.user_id " +
            " s.order_id in #{orderIdList} " +
            "group by s.goods_id;")
    void insertSoldGoods(Integer userId, List<Integer> orderIdList);
}
