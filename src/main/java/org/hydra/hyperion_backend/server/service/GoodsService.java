package org.hydra.hyperion_backend.server.service;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.GoodsAddRequest;
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

    public void add(GoodsAddRequest request) {
        int userId = ThreadLocalUtil.get();
        request.setUserId(userId);
        goodsMapper.add(request);
    }
}
