package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.entity.BaseEntity;
import lombok.Data;

@Data
public class ShortUrl extends BaseEntity {
    private String id;
    private String code;
    private String picId;
}
