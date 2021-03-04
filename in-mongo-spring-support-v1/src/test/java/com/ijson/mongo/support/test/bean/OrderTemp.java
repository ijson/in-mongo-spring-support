package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;

@Data
public class OrderTemp extends BaseEntity {

    private String id;
    private String tradeNo;
    private String userId;
    private double amount;
    private String subject;
}
