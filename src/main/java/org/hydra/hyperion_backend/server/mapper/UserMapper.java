package org.hydra.hyperion_backend.server.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hydra.hyperion_backend.pojo.dto.UserChangeRequest;
import org.hydra.hyperion_backend.pojo.dto.UserRegisterRequest;
import org.hydra.hyperion_backend.pojo.entity.User;

@Mapper
public interface UserMapper {
    @Insert("insert into user (tel, name, pass, role, email, state) " +
            "values (#{tel}, #{name}, #{pass}, #{role}, #{email}, 'ACTIVE')")
    void register(UserRegisterRequest request);

    @Select("select * from user " +
            "where tel = #{tel} and role=#{role}")
    User findByTelRole(String tel, String role);

    @Select("select * from user " +
            "where id = #{userId}")
    User findById(int userId);

    @Update("update user " +
            "set name=#{name}, tel=#{tel}, email=#{email} " +
            "where id=#{id}")
    void change(UserChangeRequest request);
}
