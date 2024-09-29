package org.hydra.hyperion_backend.server.controller;

import org.hydra.hyperion_backend.pojo.dto.UserChangeRequest;
import org.hydra.hyperion_backend.pojo.dto.UserLoginRequest;
import org.hydra.hyperion_backend.pojo.dto.UserRegisterRequest;
import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody @Validated UserRegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public Result login(@RequestBody @Validated UserLoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/get")
    public Result get() {
        return userService.get();
    }

    @PostMapping("/change")
    public Result change(@RequestBody @Validated UserChangeRequest request) {
        return userService.change(request);
    }
}
