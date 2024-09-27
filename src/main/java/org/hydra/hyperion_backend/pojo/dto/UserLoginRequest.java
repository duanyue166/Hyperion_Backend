package org.hydra.hyperion_backend.pojo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hydra.hyperion_backend.annotation.ValidRegexp;
import org.hydra.hyperion_backend.annotation.ValidState;

@Data
public class UserLoginRequest {
    @ValidRegexp(type = "tel")
    String tel;

    @NotNull
    String pass;

    @ValidState(states = {"ADMIN", "CONSUMER", "MERCHANT"})
    String role;
}
