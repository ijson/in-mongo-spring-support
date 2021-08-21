package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.entity.BaseEntity;
import lombok.Data;

@Data
public class Commnet extends BaseEntity {

    private String id;
    private String refId;
    private String title;
    private String content;
    private Long number;
}
