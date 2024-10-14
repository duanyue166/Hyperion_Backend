package org.hydra.hyperion_backend.server.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.hydra.hyperion_backend.pojo.PageBean;
import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.entity.Order;
import org.hydra.hyperion_backend.pojo.vo.AddressDetailVo;
import org.hydra.hyperion_backend.pojo.vo.OrderDetailVo;
import org.hydra.hyperion_backend.pojo.vo.OrderListItemVo;
import org.hydra.hyperion_backend.pojo.vo.SoldGoodsDetailVo;
import org.hydra.hyperion_backend.server.mapper.AddressMapper;
import org.hydra.hyperion_backend.server.mapper.OrderMapper;
import org.hydra.hyperion_backend.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    AddressMapper addressMapper;

    public Result add(Integer addrId, List<Integer> goodsIdList) {
        int userId = ThreadLocalUtil.get();

        AddressDetailVo addressDetail = addressMapper.detail(addrId);
        String address = addressDetail.getProvName()
                + addressDetail.getCityName()
                + addressDetail.getDistName()
                + addressDetail.getDetail();
        String contact = addressDetail.getContact();
        String consignee = addressDetail.getConsignee();

        List<SoldGoodsDetailVo> soldList = orderMapper.getTrolleyGoodsList(userId, goodsIdList);
        Map<Integer, List<SoldGoodsDetailVo>> groupedByMerId = soldList.stream()
                .collect(Collectors.groupingBy(SoldGoodsDetailVo::getMerId));

        for (var entry : groupedByMerId.entrySet()) {
            Integer merId = entry.getKey();
            List<SoldGoodsDetailVo> soldGoodsDetails = entry.getValue();
            createOrder(userId, merId, soldGoodsDetails, consignee, contact, address);
        }

        return Result.success();
    }

    private void createOrder(Integer userId, Integer merId, List<SoldGoodsDetailVo> soldGoodsDetails,
                             String consignee, String contact, String address) {
        double payment = soldGoodsDetails.stream()
                .mapToDouble(soldGoods -> soldGoods.getPrice() * soldGoods.getQuantity())
                .sum();

        String coverUrl = generateCover(soldGoodsDetails);

        Order order = Order.builder()
                .conId(userId)
                .merId(merId)
                .state("PLACED")
                .payment(payment)
                .consignee(consignee)
                .contact(contact)
                .address(address)
                .coverUrl(coverUrl)
                .build();

        orderMapper.addOrder(order);
        System.out.println("Order added: " + order);

        Integer orderId = (int) order.getId();
        orderMapper.addSoldGoods(orderId, soldGoodsDetails);
        System.out.println("SoldGoods added: " + soldGoodsDetails);

        //delete trolley items
        List<Integer> goodsIdList = soldGoodsDetails.stream()
                .map(SoldGoodsDetailVo::getGoodsId)
                .toList();
        orderMapper.deleteTrolley(userId, goodsIdList);
    }

    private String generateCover(List<SoldGoodsDetailVo> soldGoodsDetails) {
        //TODO: implement this
        return soldGoodsDetails.get(0).getCoverUrl();///temp
    }

    public Result pay(Integer orderId) {
        int userId = ThreadLocalUtil.get();
        orderMapper.updateState(userId, orderId, "CONFIRMED");
        return Result.success();
    }

    public Result cList(Integer pageSize, Integer pageNum, String state) {
        int userId = ThreadLocalUtil.get();
        PageHelper.startPage(pageNum, pageSize);
        var page = (Page<OrderListItemVo>) orderMapper.cList(userId, state);
        PageBean<OrderListItemVo> pageBean = new PageBean<>(page.getTotal(), page.getResult());
        return Result.success(pageBean);
    }

    public Result mList(Integer pageSize, Integer pageNum, String state) {
        int userId = ThreadLocalUtil.get();
        PageHelper.startPage(pageNum, pageSize);
        var page = (Page<OrderListItemVo>) orderMapper.mList(userId, state);
        PageBean<OrderListItemVo> pageBean = new PageBean<>(page.getTotal(), page.getResult());
        return Result.success(pageBean);
    }

    public Result ship(Integer orderId) {
        try {
            orderMapper.decreaseQuantity(orderId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Result.error("Goods quantity not enough");
        }

        int userId = ThreadLocalUtil.get();
        orderMapper.updateState(userId, orderId, "SHIPPED");
        return Result.success();
    }

    public Result receive(Integer orderId) {
        orderMapper.increaseTotSales(orderId);

        int userId = ThreadLocalUtil.get();
        orderMapper.updateState(userId, orderId, "COMPLETE");
        return Result.success();
    }

    public Result review(Integer orderId, Integer goodsId, Integer score) {
        orderMapper.review(orderId, goodsId, score);
        return Result.success();
    }

    public Result detail(Integer orderId) {
        int userId = ThreadLocalUtil.get();
        OrderDetailVo orderDetail = orderMapper.detail(userId, orderId);
        orderDetail.setGoodsList(orderMapper.getSoldGoodsList(orderId));
        return Result.success(orderDetail);
    }
}
