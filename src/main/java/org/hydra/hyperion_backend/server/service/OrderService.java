package org.hydra.hyperion_backend.server.service;


import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.entity.Address;
import org.hydra.hyperion_backend.pojo.vo.AddressDetailVo;
import org.hydra.hyperion_backend.server.mapper.AddressMapper;
import org.hydra.hyperion_backend.server.mapper.OrderMapper;
import org.hydra.hyperion_backend.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        
        return Result.success();
    }
}
