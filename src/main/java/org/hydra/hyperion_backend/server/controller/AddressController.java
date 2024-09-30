package org.hydra.hyperion_backend.server.controller;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.AddressAddRequest;
import org.hydra.hyperion_backend.server.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @PostMapping("/add")
    public Result add(@RequestBody AddressAddRequest request) {
        return addressService.add(request);
    }

    @GetMapping("/detail")
    public Result detail(Integer id){
        return addressService.detail(id);
    }

    @GetMapping("/list")
    public Result list(){
        return addressService.list();
    }
}
