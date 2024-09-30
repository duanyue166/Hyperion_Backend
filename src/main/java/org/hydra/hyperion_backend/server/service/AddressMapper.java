package org.hydra.hyperion_backend.server.service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hydra.hyperion_backend.pojo.dto.AddressAddRequest;
import org.hydra.hyperion_backend.pojo.entity.Address;
import org.hydra.hyperion_backend.pojo.vo.AddressVo;

@Mapper
public interface AddressMapper {
    @Insert("insert into address (user_id, prov_id, city_id, dist_id, detail) " +
            "values (#{userId}, #{provId}, #{cityId}, #{distId}, #{detail})")
    void add(AddressAddRequest request);

    @Select("SELECT " +
            "    (SELECT name FROM area WHERE id = a.prov_id) AS prov_name," +
            "    a.prov_id," +
            "    (SELECT name FROM area WHERE id = a.city_id) AS city_name," +
            "    a.city_id," +
            "    (SELECT name FROM area WHERE id = a.dist_id) AS dist_name," +
            "    a.dist_id," +
            "    a.detail,a.is_default,a.id " +
            "FROM " +
            "    address a " +
            "WHERE " +
            "    a.id = #{id}")
    AddressVo detail(Integer id);
}
