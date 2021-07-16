package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.entity.BaseEntity;
import lombok.Data;

@Data
public class Sensitive extends BaseEntity {

    private String id;

    private String sensitiveWord;

    private String replaceWord;
}
