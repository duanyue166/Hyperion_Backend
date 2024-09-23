package org.hydra.hyperion_backend.pojo.entity;

import lombok.Data;

@Data
public class Address {

    private long id;
    private long userId;
    private long provId;
    private long cityId;
    private long distId;
    private String detail;
    private long isDefault;
    private String state;

}
