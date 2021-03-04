package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;

@Data
public class MtData extends BaseEntity {

    private String object_describe_api_name;
    private String object_desceibe_id;
    private String name;
    private String shortUrl;

}
