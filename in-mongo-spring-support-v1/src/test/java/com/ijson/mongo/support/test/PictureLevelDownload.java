package com.ijson.mongo.support.test;

import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;

@Data
public class PictureLevelDownload extends BaseEntity {

    private String cname;
    private String code;
    private Double amount;
    private Long dailyDownload;
    private boolean forever;

}
