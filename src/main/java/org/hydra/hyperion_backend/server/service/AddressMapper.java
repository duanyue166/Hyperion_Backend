package org.hydra.hyperion_backend.server.service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hydra.hyperion_backend.pojo.dto.AddressAddRequest;
import org.hydra.hyperion_backend.pojo.entity.Address;
import org.hydra.hyperion_backend.pojo.vo.AddressDetailVo;
import org.hydra.hyperion_backend.pojo.vo.AddressListItemVo;

import java.util.List;

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
    AddressDetailVo detail(Integer id);

    @Select("SELECT  " +
            "    a.id, " +
            "    CONCAT((SELECT name FROM area WHERE id = a.prov_id), '-', " +
            "           (SELECT name FROM area WHERE id = a.city_id), '-', " +
            "           (SELECT name FROM area WHERE id = a.dist_id)) AS district, " +
            "    a.detail,a.is_default " +
            "FROM  " +
            "    address a " +
            "WHERE  " +
            "    a.user_id = #{userId}")
    List<AddressListItemVo> list(int userId);
}
