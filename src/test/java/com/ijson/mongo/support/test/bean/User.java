package com.ijson.mongo.support.test.bean;

import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by cuiyongxu on 17/8/1.
 */
@Entity(value = "user", noClassnameStored = true)
@Data
public class User {
    @Id
    private ObjectId id;
    private String username;
    private String password;

    public User(String username) {
        this.username = username;
    }
}
