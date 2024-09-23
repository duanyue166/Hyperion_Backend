package org.hydra.hyperion_backend.pojo.entity;


import lombok.Data;

@Data
public class Goods {

    private long id;
    private long userId;
    private String name;
    private String state;
    private String coverUrl;
    private String desc;
    private String category;
    private double price;
    private double discount;
    private long quantity;
    private long totSales;
    private long pCount;
    private long nCount;

}
