package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.entity.BaseEntity;
import lombok.Data;

@Data
public class Reply extends BaseEntity {

    private String id;
    private String commentId;
    private String title;
    private String content;
    private Long number;
    private String fromUserId;
    private String toUserId;
}
