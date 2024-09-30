package org.hydra.hyperion_backend.server.mapper;

import org.apache.ibatis.annotations.*;
import org.hydra.hyperion_backend.pojo.dto.TrolleyRequest;
import org.hydra.hyperion_backend.pojo.vo.TrolleyItemVo;

import java.util.List;

@Mapper
public interface TrolleyMapper {
    @Insert("insert into trolley (user_id, goods_id, quantity) " +
            "values (#{userId}, #{id}, #{quantity})")
    void add(TrolleyRequest request);

    @Select("select g.id, t.quantity, g.price, g.discount, g.cover_url, g.name " +
            "from trolley t join goods g on t.goods_id=g.id " +
            "where t.user_id=#{userId}")
    List<TrolleyItemVo> list(int userId);

    @Delete("delete from trolley " +
            "where user_id=#{userId} and goods_id=#{goodsId}")
    void delete(Integer userId, Integer goodsId);

    @Update("update trolley " +
            "set quantity=#{quantity} " +
            "where user_id=#{userId} and goods_id=#{id}")
    void update(TrolleyRequest request);
}
