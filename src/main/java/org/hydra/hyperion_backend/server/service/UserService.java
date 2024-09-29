package org.hydra.hyperion_backend.server.service;

import org.hydra.hyperion_backend.pojo.LoginUser;
import org.hydra.hyperion_backend.pojo.dto.UserChangeRequest;
import org.hydra.hyperion_backend.pojo.dto.UserLoginRequest;
import org.hydra.hyperion_backend.pojo.dto.UserRegisterRequest;
import org.hydra.hyperion_backend.pojo.entity.User;
import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.vo.UserLoginResponse;
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
        User user = userMapper.findByTelRole(request.getTel(), request.getRole());
        if (user != null)
            return Result.error("手机号码已被注册该角色");
        request.setPass(passwordEncoder.encode(request.getPass()));
        userMapper.register(request);
        return Result.success("注册成功");
    }

    public Result login(UserLoginRequest request) {
        String tel_role = request.getTel() + "," + request.getRole();
        var token = new UsernamePasswordAuthenticationToken(tel_role, request.getPass());
        Authentication authentication = authenticationManager.authenticate(token);
        var loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        var claim = Map.of("userId", userId, "loginUser", loginUser);
        ThreadLocalUtil.set(claim);

        User user = loginUser.getUser();
        String jwt = JwtUtil.genToken(Map.of("userId", userId));
        var response = UserLoginResponse.builder()
                .name(user.getName())
                .tel(user.getTel())
                .role(user.getRole())
                .token(jwt)
                .build();
        return Result.success(response);
    }


    public Result get() {
        int userId = ThreadLocalUtil.get();
        User user = userMapper.findById(userId);
        UserVo response = UserVo.builder()
                .id((int) user.getId())
                .name(user.getName())
                .tel(user.getTel())
                .email(user.getEmail())
                .build();
        return Result.success(response);
    }


    public Result change(UserChangeRequest request) {
        int userId = ThreadLocalUtil.get();
        request.setId(userId);
        userMapper.change(request);
        return Result.success("修改成功");
    }
}
