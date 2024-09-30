package org.hydra.hyperion_backend.server.service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.hydra.hyperion_backend.pojo.dto.AddressAddRequest;
import org.hydra.hyperion_backend.pojo.entity.Address;

@Mapper
public interface AddressMapper {
    @Insert("insert into address (user_id, prov_id, city_id, dist_id, detail) " +
            "values (#{userId}, #{provId}, #{cityId}, #{distId}, #{detail})")
    void add(AddressAddRequest request);


    Address detail(Integer id);
}
