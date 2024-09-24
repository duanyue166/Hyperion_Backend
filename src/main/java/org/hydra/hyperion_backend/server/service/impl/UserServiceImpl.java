package org.hydra.hyperion_backend.server.service.impl;

import org.hydra.hyperion_backend.pojo.dto.UserRegisterDTO;
import org.hydra.hyperion_backend.pojo.entity.User;
import org.hydra.hyperion_backend.pojo.vo.UserVo;
import org.hydra.hyperion_backend.server.mapper.UserMapper;
import org.hydra.hyperion_backend.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserVo registerUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setName(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setTel(userRegisterDTO.getTel());
        user.setPass(userRegisterDTO.getPass()); // 应使用加密
        user.setRole(userRegisterDTO.getRole());

        userMapper.insertUser(user);
        return UserVo.builder().username(user.getName()).email(user.getEmail()).tel(user.getTel()).role(user.getRole()).build();
    }
}
