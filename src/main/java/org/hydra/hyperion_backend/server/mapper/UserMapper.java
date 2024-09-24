package org.hydra.hyperion_backend.server.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.hydra.hyperion_backend.pojo.entity.User;

@Mapper
public interface UserMapper {


    @Insert("insert into user (tel, name, pass, role, email, state, last_login) values (#{tel}, #{name}, #{pass}, #{role}, #{email}, #{state}, #{lastLogin})")
    void insertUser(User user);
}
