package com.ijson.mongo.generator.entity;

import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;
import org.mongodb.morphia.annotations.Id;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/13 4:37 PM
 */
@Data
public class User extends BaseEntity{

    private String id;

    private String name;

    private Integer age;

}
