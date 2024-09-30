package org.hydra.hyperion_backend.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hydra.hyperion_backend.pojo.entity.Area;

import java.util.List;

@Mapper
public interface AreaMapper {
    @Select("select * from area " +
            "where parent_id = #{parentId}")
    List<Area> list(Integer parentId);
}
