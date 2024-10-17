package org.hydra.hyperion_backend.server.controller;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.TrolleyRequest;
import org.hydra.hyperion_backend.server.service.TrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trolley")
@PreAuthorize("hasAuthority('CONSUMER')")
public class TrolleyController {
    @Autowired
    TrolleyService trolleyService;

    @PostMapping("/add")
    public Result add(@RequestBody @Validated TrolleyRequest request) {
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

    @PutMapping("/update")
    public Result update(@RequestBody @Validated TrolleyRequest request) {
        return trolleyService.update(request);
    }
}
