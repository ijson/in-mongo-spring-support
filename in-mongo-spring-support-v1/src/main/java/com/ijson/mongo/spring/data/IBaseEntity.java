package com.ijson.mongo.spring.data;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class IBaseEntity {

    @Id
    protected String id;


    private boolean enable;

    private Boolean deleted;

    private String createdBy;

    private long createTime;

    private String lastModifiedBy;

    private long lastModifiedTime;


}
