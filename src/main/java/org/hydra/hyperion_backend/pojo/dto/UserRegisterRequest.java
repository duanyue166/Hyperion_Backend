package org.hydra.hyperion_backend.pojo.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hydra.hyperion_backend.annotation.ValidRegexp;
import org.hydra.hyperion_backend.annotation.ValidState;

@Data
public class UserRegisterRequest {
    @ValidRegexp(type = "tel")
    private String tel;

    @ValidRegexp(type = "username")
    private String name;

    //    @ValidRegexp(type = "password", message = "invalid password")
    private String pass;

    @ValidState(type = "role")
    private String role;

    @Email
    private String email;
}
