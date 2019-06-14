package com.ijson.mongo.support.model;

import lombok.Data;
import org.mongodb.morphia.annotations.Id;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/13 4:34 PM
 */
@Data
public class BaseEntity {
    @Id
    private String id;
}
