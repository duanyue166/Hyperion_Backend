package org.hydra.hyperion_backend.server.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hydra.hyperion_backend.pojo.dto.UserRegisterRequest;
import org.hydra.hyperion_backend.pojo.entity.User;

@Mapper
public interface UserMapper {
    @Insert("insert into user (tel, name, pass, role, email, state) " +
            "values (#{tel}, #{name}, #{pass}, #{role}, #{email}, 'ACTIVE')")
    void register(UserRegisterRequest request);

    @Select("select * from user where tel = #{tel}")
    User findByTel(String tel);
}
