package org.hydra.hyperion_backend.pojo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hydra.hyperion_backend.annotation.ValidRegexp;

@Data
public class UserLoginRequest {
    @ValidRegexp(type = "tel")
    String tel;

    @NotNull
    String pass;
}
