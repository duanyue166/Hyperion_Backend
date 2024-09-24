package org.hydra.hyperion_backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.validator")
public class ValidatorConfig {
    @Data
    public static class BaseValidator {
        String regexp;
        String message;
    }

    public Username username;
    public Password password;
    public Tel tel;

    public static class Password extends BaseValidator {
    }

    public static class Username extends BaseValidator {
    }

    public static class Tel extends BaseValidator {
    }
}
