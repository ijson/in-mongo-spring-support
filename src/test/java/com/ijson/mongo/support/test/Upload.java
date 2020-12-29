package com.ijson.mongo.support.test;

import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;

@Data
public class Upload extends BaseEntity {

    private String id;
    private String md5;
    private String fileName;
    private String path;
    private long fileSize;
    private String suffix;

}
