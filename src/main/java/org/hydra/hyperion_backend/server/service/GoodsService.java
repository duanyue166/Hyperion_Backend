package org.hydra.hyperion_backend.server.service;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.GoodsRequest;
import org.hydra.hyperion_backend.pojo.entity.Goods;
import org.hydra.hyperion_backend.server.mapper.GoodsMapper;
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

    public Result add(GoodsRequest request) {
        int userId = ThreadLocalUtil.get();
        request.setUserId(userId);
        goodsMapper.add(request);
        return Result.success();
    }

    public Result setState(Integer id, String state) {
        goodsMapper.setState(id, state);
        return Result.success();
    }

    public Result update(GoodsRequest request) {
        int userId = ThreadLocalUtil.get();
        request.setUserId(userId);
        goodsMapper.update(request);
        return Result.success();
    }
}
