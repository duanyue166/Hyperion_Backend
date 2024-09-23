package org.hydra.hyperion_backend.server.controller;


import lombok.extern.slf4j.Slf4j;
import org.hydra.hyperion_backend.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;


}
