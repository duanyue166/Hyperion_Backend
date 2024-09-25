package org.hydra.hyperion_backend.server.service;

import org.hydra.hyperion_backend.pojo.LoginUser;
import org.hydra.hyperion_backend.pojo.dto.UserLoginRequest;
import org.hydra.hyperion_backend.pojo.dto.UserRegisterRequest;
import org.hydra.hyperion_backend.pojo.entity.User;
import org.hydra.hyperion_backend.pojo.vo.Result;
import org.hydra.hyperion_backend.pojo.vo.UserVo;
import org.hydra.hyperion_backend.server.mapper.UserMapper;
import org.hydra.hyperion_backend.util.JwtUtil;
import org.hydra.hyperion_backend.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    public Result register(UserRegisterRequest request) {
        User user = userMapper.findByTel(request.getTel());
        if (user != null)
            return Result.error("手机号码已被注册");
        request.setPass(passwordEncoder.encode(request.getPass()));
        userMapper.register(request);
        return Result.success("注册成功");
    }

    public Result login(UserLoginRequest request) {
        var token = new UsernamePasswordAuthenticationToken(request.getTel(), request.getPass());
        Authentication authentication = authenticationManager.authenticate(token);
        var loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        var claim = Map.of("userId", userId, "loginUser", loginUser);
        ThreadLocalUtil.set(claim);
        String jwt = JwtUtil.genToken(Map.of("userId", userId));
        UserVo userVo = UserVo.builder().name(loginUser.getUser().getName()).tel(loginUser.getUser().getTel())
                .role(loginUser.getRole()).token(jwt).build();
        return Result.success(userVo);
    }
}
