package org.hydra.hyperion_backend.pojo.dto;


import lombok.Data;

@Data
public class UserRegisterDTO {
    private String username;
    private String tel;
    private String email;
    private String pass;
    private String role;
}
