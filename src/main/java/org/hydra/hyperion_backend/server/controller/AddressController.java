package org.hydra.hyperion_backend.server.controller;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.AddressRequest;
import org.hydra.hyperion_backend.server.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @PostMapping("/add")
    public Result add(@RequestBody @Validated AddressRequest request) {
        return addressService.add(request);
    }

    @GetMapping("/detail")
    public Result detail(Integer id) {
        return addressService.detail(id);
    }

    @GetMapping("/list")
    public Result list() {
        return addressService.list();
    }

    @PostMapping("/default")
    public Result setDefault(Integer id) {
        return addressService.setDefault(id);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Validated(AddressRequest.Update.class) AddressRequest request) {
        return addressService.update(request);
    }

    @DeleteMapping("/delete")
    public Result delete(Integer id) {
        return addressService.delete(id);
    }
}
