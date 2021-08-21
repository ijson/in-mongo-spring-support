package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.entity.BaseEntity;
import lombok.Data;

@Data
public class Tag extends BaseEntity {

    private String id;

    private String cname;

    private String count;

}
