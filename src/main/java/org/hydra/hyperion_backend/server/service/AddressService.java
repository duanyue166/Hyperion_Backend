package org.hydra.hyperion_backend.server.service;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.pojo.dto.AddressRequest;
import org.hydra.hyperion_backend.pojo.vo.AddressDetailVo;
import org.hydra.hyperion_backend.server.mapper.AddressMapper;
import org.hydra.hyperion_backend.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
public class AddressService {
    @Autowired
    AddressMapper addressMapper;

    public Result add(AddressRequest request) {
        request.setUserId(ThreadLocalUtil.get());
        addressMapper.add(request);
        return Result.success();
    }

    public Result detail(Integer id) {
        AddressDetailVo addressVo = addressMapper.detail(id);
        return Result.success(addressVo);
    }

    public Result list() {
        int userId = ThreadLocalUtil.get();
        var res = addressMapper.list(userId);
        return Result.success(res);
    }

    public Result setDefault(Integer id) {
        int userId = ThreadLocalUtil.get();
        addressMapper.setDefault(userId, id);
        return Result.success();
    }

    public Result update(AddressRequest request) {
        request.setUserId(ThreadLocalUtil.get());
        addressMapper.update(request);
        return Result.success();
    }

    public Result delete(Integer id) {
        int userId = ThreadLocalUtil.get();
        addressMapper.delete(userId, id);
        return Result.success();
    }
}
