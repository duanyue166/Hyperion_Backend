package org.hydra.hyperion_backend.pojo.entity;/* 输入的address id */;

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
