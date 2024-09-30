package org.hydra.hyperion_backend.server.controller;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.TrolleyAddRequest;
import org.hydra.hyperion_backend.server.mapper.TrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trolley")
public class TrolleyController {
    @Autowired
    TrolleyService trolleyService;

    @PostMapping("/add")
    public Result add(@RequestBody @Validated TrolleyAddRequest request) {
        return trolleyService.add(request);
    }

    @GetMapping("/list")
    public Result list() {
        return trolleyService.list();
    }

    @DeleteMapping("/delete")
    public Result delete(Integer goodsId) {
        return trolleyService.delete(goodsId);
    }
}
