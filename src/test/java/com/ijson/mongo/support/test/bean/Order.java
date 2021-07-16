package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.entity.BaseEntity;
import lombok.Data;

@Data
public class Order extends BaseEntity {

    private String id;
    private String tradeNo;
    private String userId;
    private double amount;
    private String subject;
    private String levelId;
}
