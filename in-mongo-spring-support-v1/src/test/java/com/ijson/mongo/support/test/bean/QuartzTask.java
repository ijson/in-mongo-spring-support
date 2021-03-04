package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;

@Data
public class QuartzTask extends BaseEntity {
    private String cname;
    private String description;
    private String cronExpression;
    private String beanClass;
}
