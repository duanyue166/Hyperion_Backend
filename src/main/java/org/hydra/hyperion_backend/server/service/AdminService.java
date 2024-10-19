package org.hydra.hyperion_backend.server.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.hydra.hyperion_backend.pojo.PageBean;
import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.entity.User;
import org.hydra.hyperion_backend.server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * author： Shuowei Hou
 * date： 2024/10/16
 * description：
 */
@Service
@Transactional
public class AdminService {
    @Autowired
    UserMapper userMapper;

    public Result userDelete(Integer id) {
        userMapper.delete(id);
        return Result.success();
    }

    public Result userList(Integer pageSize, Integer pageNum,
                           String role, String state, String search) {
        String searchField = null;
        String searchValue = null;
        if (search != null) {
            try {
                String[] searchParts = search.split(":");
                searchField = searchParts[0];
                searchValue = searchParts[1];
            } catch (Exception e) {
                throw new RuntimeException("搜索字符串格式错误");
            }
        }

        PageHelper.startPage(pageNum, pageSize);
        var page = (Page<User>) userMapper.list(role, state, searchField, searchValue);
        var pageBean = new PageBean<>(page.getTotal(), page.getResult());
        return Result.success(pageBean);
    }
}
