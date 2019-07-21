package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

/**
 * Created by cuiyongxu on 17/8/1.
 */
@Data
public class User extends BaseEntity{
    @Id
    private String id;
    private String username;
    private String password;
    private List<Pojo> pojoList;

    public User(String username, String password,List<Pojo> pojoList) {
        this.username = username;
        this.password = password;
        this.pojoList = pojoList;
    }

    public User() {
    }
}
