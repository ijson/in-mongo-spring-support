package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;

import java.util.List;


@Data
public class Article extends BaseEntity {

    private String id;

    private String title;

    private String content;

    private String remark;

    private String imageId;

    private List<String> tagIds;

    private String status;

    private String reason;

    private String draftId;

    private long digg;


}

