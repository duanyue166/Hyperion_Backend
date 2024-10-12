package org.hydra.hyperion_backend.server.controller;


import lombok.extern.slf4j.Slf4j;
import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.OrderAddRequest;
import org.hydra.hyperion_backend.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/pay")
    Result pay(Integer id) {
        return orderService.pay(id);
    }

    @GetMapping("/consumer/list")
    Result cList(Integer pageSize, Integer pageNum, @RequestParam(required = false) String state) {
        return orderService.cList(pageSize, pageNum, state);
    }

    @GetMapping("/merchant/list")
    Result mList(Integer pageSize, Integer pageNum, @RequestParam(required = false) String state) {
        return orderService.mList(pageSize, pageNum, state);
    }

    @PatchMapping("/ship")
    Result ship(Integer id) {
        return orderService.ship(id);
    }

    @PatchMapping("/receive")
    Result receive(Integer id) {
        return orderService.receive(id);
    }

    @PatchMapping("/review")
    Result review(Integer orderId, Integer goodsId, Integer score) {
        return orderService.review(orderId, goodsId, score);
    }
}
