package org.hydra.hyperion_backend.server.mapper;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.TrolleyAddRequest;
import org.hydra.hyperion_backend.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrolleyService {
    @Autowired
    TrolleyMapper trolleyMapper;

    public Result add(TrolleyAddRequest request) {
        request.setUserId(ThreadLocalUtil.get());
        trolleyMapper.add(request);
        return Result.success();
    }
}