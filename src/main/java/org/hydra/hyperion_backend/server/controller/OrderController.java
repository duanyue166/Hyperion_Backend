package org.hydra.hyperion_backend.server.controller;


import lombok.extern.slf4j.Slf4j;
import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.OrderAddRequest;
import org.hydra.hyperion_backend.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    Result add(@RequestBody OrderAddRequest request) {
        System.out.println(request);
        return orderService.add(request.getAddrId(), request.getGoodsIdList());
    }
}
