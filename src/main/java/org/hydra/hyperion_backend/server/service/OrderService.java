package org.hydra.hyperion_backend.server.service;


import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.entity.Order;
import org.hydra.hyperion_backend.pojo.vo.AddressDetailVo;
import org.hydra.hyperion_backend.pojo.vo.SoldGoodsDetailVo;
import org.hydra.hyperion_backend.server.mapper.AddressMapper;
import org.hydra.hyperion_backend.server.mapper.GoodsMapper;
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
    @Autowired
    GoodsService goodsService;
    @Autowired
    private GoodsMapper goodsMapper;

    public Result add(Integer addrId, List<Integer> goodsIdList) {
        int userId = ThreadLocalUtil.get();

        AddressDetailVo addressDetail = addressMapper.detail(addrId);
        String address = addressDetail.getProvName()
                + addressDetail.getCityName()
                + addressDetail.getDistName()
                + addressDetail.getDetail();
        String contact = addressDetail.getContact();
        String consignee = addressDetail.getConsignee();

        List<SoldGoodsDetailVo> soldList = orderMapper.getSoldGoodsDetails(userId, goodsIdList);
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
}
