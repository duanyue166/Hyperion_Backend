package org.hydra.hyperion_backend.server.controller;

import org.hydra.hyperion_backend.pojo.dto.UserRegisterDTO;
import org.hydra.hyperion_backend.pojo.entity.User;
import org.hydra.hyperion_backend.pojo.vo.UserVo;
import org.hydra.hyperion_backend.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        UserVo userVO = userService.registerUser(userRegisterDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 0);
        response.put("msg", "Success");
        response.put("data", userVO);
        return ResponseEntity.ok(response);
    }
}
