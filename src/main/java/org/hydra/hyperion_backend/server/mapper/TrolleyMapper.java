package org.hydra.hyperion_backend.server.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hydra.hyperion_backend.pojo.dto.TrolleyAddRequest;
import org.hydra.hyperion_backend.pojo.vo.TrolleyItemVo;

import java.util.List;

@Mapper
public interface TrolleyMapper {
    @Insert("insert into trolley (user_id, goods_id, quantity) " +
            "values (#{userId}, #{id}, #{quantity})")
    void add(TrolleyAddRequest request);

    @Select("select g.id, t.quantity, g.price, g.discount, g.cover_url, g.name " +
            "from trolley t join goods g on t.goods_id=g.id " +
            "where t.user_id=#{userId}")
    List<TrolleyItemVo> list(int userId);

    @Delete("delete from trolley " +
            "where user_id=#{userId} and goods_id=#{goodsId}")
    void delete(Integer userId, Integer goodsId);
}
