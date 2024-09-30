package org.hydra.hyperion_backend.server.controller;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.server.service.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiscController {
    @Autowired
    MiscService miscService;

    @GetMapping("/area/list")
    public Result areaList(Integer parentId) {
        return miscService.areaList(parentId);
    }
}
