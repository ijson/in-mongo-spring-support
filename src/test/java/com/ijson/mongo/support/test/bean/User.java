package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.entity.BaseEntity;
import lombok.Data;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;
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
    private Date date = new Date();

    public User(String username, String password,List<Pojo> pojoList) {
        this.username = username;
        this.password = password;
        this.pojoList = pojoList;
    }

    public User() {
    }
}
