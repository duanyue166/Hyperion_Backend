package org.hydra.hyperion_backend.server.service;

import org.hydra.hyperion_backend.pojo.LoginUser;
import org.hydra.hyperion_backend.pojo.entity.User;
import org.hydra.hyperion_backend.server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String tel_role) throws UsernameNotFoundException {
        String[] split = tel_role.split(",");
        String tel = split[0];
        String role = split[1];
        System.out.println("loadUserByUsername@UserDetailServiceImpl tel: " + tel + " role: " + role);
        User user = userMapper.findByTelRole(tel, role);
        System.out.println("loadUserByUsername@UserDetailServiceImpl user: " + user);
        if (user == null)
            throw new UsernameNotFoundException("用户不存在");
        return new LoginUser(user);
    }
}
