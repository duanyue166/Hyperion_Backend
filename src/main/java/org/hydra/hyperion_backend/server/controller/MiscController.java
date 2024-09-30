package org.hydra.hyperion_backend.server.controller;

import org.hydra.hyperion_backend.pojo.Result;
import org.hydra.hyperion_backend.server.service.MiscService;
import org.hydra.hyperion_backend.util.AliOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
public class MiscController {
    @Autowired
    MiscService miscService;

    @GetMapping("/area/list")
    public Result areaList(Integer parentId) {
        return miscService.areaList(parentId);
    }

    @PostMapping("/file/upload")
    public Result fileUpload(MultipartFile file) throws IOException {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = uuid + suffix;

//        file.transferTo(new File(appConfig.getFileUpload().getUploadPath() + filename));
        String url = AliOssUtil.uploadFile(filename, file.getInputStream());
        return Result.success(url);
    }
}
