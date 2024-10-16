package org.hydra.hyperion_backend;

import org.hydra.hyperion_backend.annotation.ValidState;
import org.hydra.hyperion_backend.util.AnnotationUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class HyperionBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyperionBackendApplication.class, args);
    }

}
