package org.hydra.hyperion_backend.server.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hydra.hyperion_backend.pojo.dto.GoodsAddRequest;
import org.hydra.hyperion_backend.pojo.entity.Goods;

/**
 * Created with IntelliJ IDEA.
 * author： Shuowei Hou
 * date： 2024/9/29
 * description：
 */
@Mapper
public interface GoodsMapper {

    @Insert("insert into goods (category, cover_url, `desc`, discount, name, price, quantity, state, user_id) " +
            "values (#{category}, #{coverUrl}, #{desc}, #{discount}, #{name}, #{price}, #{quantity}, #{state}, #{userId})")
    void add(GoodsAddRequest request);

    @Select("select * from goods " +
            "where id=#{id}")
    Goods getById(Integer id);

    @Update("update goods " +
            "set category=#{category}, cover_url=#{coverUrl}, `desc`=#{desc}, discount=#{discount}," +
            "   name=#{name}, price=#{price}, quantity=#{quantity}, state=#{state} " +
            "where id=#{id}")
    void update(Goods goods);
}
