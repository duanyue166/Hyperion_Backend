package org.hydra.hyperion_backend.pojo.entity;


import lombok.Data;

import java.util.Date;


@Data
public class User {

    private long id;
    private String tel;
    private String name;
    private String pass;
    private String role;
    private String email;
    private String state;
    private Date lastLogin;

}
