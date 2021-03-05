package com.ijson.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class BaseEntity {

    @Id
    public String id;

    public boolean enable;

    public Boolean deleted;

    public String createdBy;

    public long createTime;

    public String lastModifiedBy;

    public long lastModifiedTime;


}
