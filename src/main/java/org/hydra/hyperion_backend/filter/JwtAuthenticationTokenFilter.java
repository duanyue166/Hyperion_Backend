package org.hydra.hyperion_backend.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hydra.hyperion_backend.pojo.LoginUser;
import org.hydra.hyperion_backend.server.mapper.UserMapper;
import org.hydra.hyperion_backend.util.JwtUtil;
import org.hydra.hyperion_backend.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        System.out.println("doFilterInternal@JwtAuthenticationTokenFilter token: " + token);
        /*
         * 如果请求头中包含Authorization字段，且字段值以Bearer开头
         * 则解析token，获取其中的userId
         */
        if (token != null && token.startsWith("Bearer ")) {
            var claim = JwtUtil.parseToken(token.substring(7));
            var userId = (int) claim.get("userId");

            ThreadLocalUtil.set(userId);

            /*
             * 从数据库中获取userId对应的用户信息
             * 并将用户信息封装到LoginUser对象中
             */
            var user = userMapper.findById(userId);
            var loginUser = new LoginUser(user);

            var authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}