package org.hydra.hyperion_backend.server.service;

import org.hydra.hyperion_backend.pojo.dto.UserRegisterDTO;
import org.hydra.hyperion_backend.pojo.entity.User;
import org.hydra.hyperion_backend.pojo.vo.UserVo;
import org.springframework.stereotype.Service;


public interface UserService {
    public UserVo registerUser(UserRegisterDTO userRegisterDTO);
}
