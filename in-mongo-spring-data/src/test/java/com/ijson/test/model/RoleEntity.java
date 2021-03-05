package com.ijson.test.model;

import com.ijson.mongo.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "xx_role")
public class RoleEntity extends BaseEntity {
    private String name;

}