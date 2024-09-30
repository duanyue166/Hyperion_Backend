package org.hydra.hyperion_backend.server.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hydra.hyperion_backend.pojo.dto.AddressRequest;
import org.hydra.hyperion_backend.pojo.vo.AddressDetailVo;
import org.hydra.hyperion_backend.pojo.vo.AddressListItemVo;

import java.util.List;

@Mapper
public interface AddressMapper {
    @Insert("insert into address (user_id, prov_id, city_id, dist_id, detail) " +
            "values (#{userId}, #{provId}, #{cityId}, #{distId}, #{detail})")
    void add(AddressRequest request);

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
            "    a.id = #{id} and a.state='ACTIVE'")
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
            "    a.user_id = #{userId} and a.state='ACTIVE'")
    List<AddressListItemVo> list(int userId);

    void setDefault(int userId, int id);

    @Update("update address " +
            "set prov_id = #{provId}, city_id = #{cityId}, dist_id = #{distId}, detail = #{detail} " +
            "where id = #{id} and user_id=#{userId}")
    void update(AddressRequest request);

    @Update("update address " +
            "set state = 'DELETED' " +
            "where id=#{id} and user_id = #{userId}")
    void delete(int userId, Integer id);
}
