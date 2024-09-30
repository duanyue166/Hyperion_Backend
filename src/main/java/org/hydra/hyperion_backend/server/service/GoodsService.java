package org.hydra.hyperion_backend.server.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.hydra.hyperion_backend.pojo.PageBean;
import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.GoodsRequest;
import org.hydra.hyperion_backend.pojo.entity.Goods;
import org.hydra.hyperion_backend.server.mapper.GoodsMapper;
import org.hydra.hyperion_backend.server.mapper.UserMapper;
import org.hydra.hyperion_backend.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * author： Shuowei Hou
 * date： 2024/9/29
 * description：
 */
@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    private UserMapper userMapper;

    public Result add(GoodsRequest request) {
        int userId = ThreadLocalUtil.get();
        request.setUserId(userId);
        goodsMapper.add(request);
        return Result.success();
    }

    public Result setSale(Integer id, String sale) {
        goodsMapper.setSale(id, sale);
        return Result.success();
    }

    public Result update(GoodsRequest request) {
        int userId = ThreadLocalUtil.get();
        request.setUserId(userId);
        goodsMapper.update(request);
        return Result.success();
    }

    public Result setState(Integer id, String state) {
        goodsMapper.setState(id, state);
        return Result.success();
    }

    public Result detail(Integer id) {
        Goods goods = goodsMapper.getById(id);
        return Result.success(goods);
    }

    public PageBean<Goods> listAll(Integer pageSize, Integer pageNum, String category) {
        PageHelper.startPage(pageNum, pageSize);
        var page = (Page<Goods>) goodsMapper.list(category);
        return new PageBean<>(page.getTotal(), page.getResult());
    }
}
