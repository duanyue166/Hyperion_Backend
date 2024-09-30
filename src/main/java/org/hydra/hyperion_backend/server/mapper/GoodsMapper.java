package org.hydra.hyperion_backend.server.mapper;

import org.apache.ibatis.annotations.*;
import org.hydra.hyperion_backend.pojo.dto.GoodsRequest;
import org.hydra.hyperion_backend.pojo.entity.Goods;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * author： Shuowei Hou
 * date： 2024/9/29
 * description：
 */
@Mapper
public interface GoodsMapper {

    @Insert("insert into goods (category, cover_url, `desc`, discount, name, price, quantity, user_id) " +
            "values (#{category}, #{coverUrl}, #{desc}, #{discount}, #{name}, #{price}, #{quantity}, #{userId})")
    void add(GoodsRequest request);

    @Select("select * from goods " +
            "where id=#{id}")
    Goods getById(Integer id);

    @Update("update goods " +
            "set category=#{category}, cover_url=#{coverUrl}, `desc`=#{desc}, discount=#{discount}," +
            "   name=#{name}, price=#{price}, quantity=#{quantity} " +
            "where id=#{id} and user_id=#{userId}")
    void update(GoodsRequest request);

    @Update("update goods " +
            "set sale=#{sale} " +
            "where id=#{id}")
    void setSale(Integer id, String sale);

    @Update("update goods " +
            "set state=#{state} " +
            "where id=#{id}")
    void setState(Integer id, String state);

    List<Goods> list(String category);
}
