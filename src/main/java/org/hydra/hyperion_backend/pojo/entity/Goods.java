package org.hydra.hyperion_backend.pojo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Goods {

    private long id;
    private long userId;
    private String name;
    private String state;
    private String sale;
    private String coverUrl;
    private String desc;
    private String category;
    private double price;
    private double discount;
    private long quantity;
    private long totSales;
    private long posCount;
    private long negCount;

}
