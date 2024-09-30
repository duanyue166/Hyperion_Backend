package org.hydra.hyperion_backend.server.controller;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.GoodsAddRequest;
import org.hydra.hyperion_backend.server.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * author： Shuowei Hou
 * date： 2024/9/29
 * description：
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @PostMapping("/add")
    public Result add(@RequestBody @Validated GoodsAddRequest request) {
        return goodsService.add(request);
    }

    @PutMapping("/onsale")
    public Result onsale(Integer id) {
        return goodsService.setState(id, "ONSALE");
    }

    @PutMapping("/offsale")
    public Result offsale(Integer id) {
        return goodsService.setState(id, "OFFSALE");
    }
}
