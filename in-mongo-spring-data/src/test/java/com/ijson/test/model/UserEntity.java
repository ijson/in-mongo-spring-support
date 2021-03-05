package com.ijson.test.model;

import com.ijson.mongo.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "xx_user")
public class UserEntity extends BaseEntity {

    private String username;
    private String password;
    private String mobile;
    @DBRef
    private RoleEntity role;


}
