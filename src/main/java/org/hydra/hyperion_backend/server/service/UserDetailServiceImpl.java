package org.hydra.hyperion_backend.server.service;

import org.hydra.hyperion_backend.pojo.LoginUser;
import org.hydra.hyperion_backend.pojo.entity.User;
import org.hydra.hyperion_backend.server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String tel) throws UsernameNotFoundException {
        User user = userMapper.findByTel(tel);
        if (user == null)
            throw new UsernameNotFoundException("用户不存在");
        return new LoginUser(user);
    }
}
