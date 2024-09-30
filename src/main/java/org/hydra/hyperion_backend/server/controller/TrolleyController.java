package org.hydra.hyperion_backend.server.controller;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.TrolleyAddRequest;
import org.hydra.hyperion_backend.server.mapper.TrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trolley")
public class TrolleyController {
    @Autowired
    TrolleyService trolleyService;

    @PostMapping("/add")
    public Result add(@RequestBody @Validated TrolleyAddRequest request){
        return trolleyService.add(request);
    }
}