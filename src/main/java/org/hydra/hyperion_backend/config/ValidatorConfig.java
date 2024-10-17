package org.hydra.hyperion_backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.validator")
public class ValidatorConfig {
    ValidRegexp validRegexp;
    ValidState validState;

    @Data
    public static class ValidRegexp {
        Username username;
        Password password;
        Tel tel;

        @Data
        public static class Base {
            String regexp;
            String message;
        }

        public static class Password extends Base {
        }

        public static class Username extends Base {
        }

        public static class Tel extends Base {
        }
    }

    @Data
    public static class ValidState {
        List<String> role;
        List<String> state;
    }
}
