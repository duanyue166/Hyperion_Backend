package org.hydra.hyperion_backend.pojo.entity;


import lombok.Data;

@Data
public class Area {

    private long id;
    private String name;
    private long type;
    private long parentId;

}
