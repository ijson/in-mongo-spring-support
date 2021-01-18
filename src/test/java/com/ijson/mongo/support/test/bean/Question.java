package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.generator.model.DocDescribe;
import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class Question extends BaseEntity {

    private String id;
    @DocDescribe("标题")
    private String title;
    private String content;
    private Long view;
    private Long praise;
    private Long collect;
    private Long answer;
    private String categoryId;
    private Long reward;
    private List<String> tagIds;

}
