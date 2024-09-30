package org.hydra.hyperion_backend.server.service;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.GoodsAddRequest;
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

    public Result add(GoodsAddRequest request) {
        int userId = ThreadLocalUtil.get();
        request.setUserId(userId);
        goodsMapper.add(request);
        return Result.success();
    }

    public Result setState(Integer id, String state) {
        Goods goods = goodsMapper.getById(id);
        goods.setState(state);
        goodsMapper.update(goods);
        return Result.success();
    }
}
