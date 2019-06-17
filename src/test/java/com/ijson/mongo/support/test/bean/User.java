package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by cuiyongxu on 17/8/1.
 */
public class User extends BaseEntity{
    @Id
    private String id;
    private String username;
    private String password;

}
