package org.hydra.hyperion_backend;

import org.hydra.hyperion_backend.config.ValidatorConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HyperionBackendApplicationTests {
    @Autowired
    ValidatorConfig config;

    @Test
    void contextLoads() throws NoSuchFieldException, IllegalAccessException {
        var field=config.getClass().getField("password");
        System.out.println(field.get(config));
    }

}
