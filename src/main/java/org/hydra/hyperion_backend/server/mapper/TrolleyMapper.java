package org.hydra.hyperion_backend.server.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.hydra.hyperion_backend.pojo.dto.TrolleyAddRequest;

@Mapper
public interface TrolleyMapper {
    @Insert("insert into trolley (user_id, goods_id, quantity) " +
            "values (#{userId}, #{id}, #{quantity})")
    void add(TrolleyAddRequest request);
}
