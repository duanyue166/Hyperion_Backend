package org.hydra.hyperion_backend.server.controller;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * author： Shuowei Hou
 * date： 2024/10/16
 * description：
 */
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    AdminService adminService;

    @DeleteMapping("/user/delete")
    public Result userDelete(Integer id) {
        return adminService.userDelete(id);
    }

    @GetMapping("/user/list")
    public Result userList(Integer pageSize, Integer pageNum, String role, String state) {
        return adminService.userList(pageSize, pageNum, role, state);
    }
}
