package org.hydra.hyperion_backend.server.controller;

import org.hydra.hyperion_backend.pojo.PageBean;
import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.GoodsRequest;
import org.hydra.hyperion_backend.pojo.entity.Goods;
import org.hydra.hyperion_backend.server.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('MERCHANT')")
    public Result add(@RequestBody @Validated GoodsRequest request) {
        return goodsService.add(request);
    }

    @PutMapping("/onsale")
    @PreAuthorize("hasAuthority('MERCHANT')")
    public Result onsale(Integer id) {
        return goodsService.setSale(id, "ON");
    }

    @PutMapping("/offsale")
    @PreAuthorize("hasAuthority('MERCHANT')")
    public Result offsale(Integer id) {
        return goodsService.setSale(id, "OFF");
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('MERCHANT')")
    public Result update(@RequestBody @Validated GoodsRequest request) {
        System.out.println(request);
        return goodsService.update(request);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('MERCHANT')")
    public Result delete(Integer id) {
        return goodsService.setState(id, "DELETED");
    }

    @GetMapping("/detail")
    public Result detail(Integer id) {
        return goodsService.detail(id);
    }

    @GetMapping("/listAll")
    public Result listAll(
            Integer pageSize,
            Integer pageNum,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category) {
        var pageBean = goodsService.listAll(pageSize, pageNum, keyword, category);
        return Result.success(pageBean);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('MERCHANT')")
    public Result list(
            Integer pageSize,
            Integer pageNum) {
        var pageBean = goodsService.list(pageSize, pageNum);
        return Result.success(pageBean);
    }
}
