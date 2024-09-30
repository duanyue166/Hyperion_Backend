package org.hydra.hyperion_backend.server.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.hydra.hyperion_backend.pojo.dto.GoodsAddRequest;

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
}
