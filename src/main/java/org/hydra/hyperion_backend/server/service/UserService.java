package org.hydra.hyperion_backend.server.service;

import org.hydra.hyperion_backend.pojo.dto.UserRegisterRequest;
import org.hydra.hyperion_backend.pojo.entity.User;
import org.hydra.hyperion_backend.pojo.vo.Result;
import org.hydra.hyperion_backend.server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public Result register(UserRegisterRequest request) {
        User user = userMapper.findByTel(request.getTel());
        if (user != null)
            return Result.error("手机号码已被注册");
        userMapper.register(request);
        return Result.success("注册成功");
    }
}
