package org.hydra.hyperion_backend.server.service;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.AddressAddRequest;
import org.hydra.hyperion_backend.pojo.entity.Address;
import org.hydra.hyperion_backend.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    AddressMapper addressMapper;

    public Result add(AddressAddRequest request) {
        request.setUserId(ThreadLocalUtil.get());
        addressMapper.add(request);
        return Result.success();
    }

    public Result detail(Integer id) {
        Address address=addressMapper.detail(id);
        return Result.success(address);
    }
}
