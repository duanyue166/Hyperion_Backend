package org.hydra.hyperion_backend.pojo.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goods {

    public static final Integer NORMAL =1;
    public static final Integer DELETED = 0;


    private int GoodsID;
    private int userID;
    private String name;
    private String cover_URL;
    private String description;
    private String category;
    private double price;
    private double discount;
    private int quantity;
    private int totalSold;
    private int positiveReview;
    private int negativeReview;
    private int state;
}
