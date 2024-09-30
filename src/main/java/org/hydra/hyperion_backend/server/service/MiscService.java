package org.hydra.hyperion_backend.server.service;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.entity.Area;
import org.hydra.hyperion_backend.pojo.vo.AreaVo;
import org.hydra.hyperion_backend.server.mapper.AreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiscService {
    @Autowired
    AreaMapper areaMapper;

    public Result areaList(Integer parentId) {
        List<Area> res = areaMapper.list(parentId);
        List<AreaVo> list = res.stream()
                .map(area -> AreaVo.builder()
                        .id((int) area.getId())
                        .name(area.getName())
                        .build())
                .toList();
        return Result.success(list);
    }
}
