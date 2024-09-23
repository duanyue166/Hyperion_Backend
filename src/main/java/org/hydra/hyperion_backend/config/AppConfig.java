package org.hydra.hyperion_backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private JwtConfig jwt;
    private AliyunConfig aliyun;

    @Data
    public static class JwtConfig {
        private String key;
        private String expiredTime;
    }

    @Data
    public static class AliyunConfig {
        private String endpoint;
        private String bucketName;
        private String accessKeyId;
        private String accessKeySecret;
    }
}
