package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.generator.model.DocDescribe;
import com.ijson.mongo.support.entity.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class Question extends BaseEntity {

    private String id;
    @DocDescribe("标题")
    private String title;
    @DocDescribe("内容")
    private String content;
    @DocDescribe("阅读")
    private Long view;
    @DocDescribe("点赞")
    private Long praise;
    @DocDescribe("收藏")
    private Long collect;
    @DocDescribe("回答总数")
    private Long answer;
    @DocDescribe("分类id")
    private String categoryId;
    @DocDescribe("奖励")
    private Long reward;
    @DocDescribe("标签")
    private List<String> tagIds;
    @DocDescribe("状态,已采纳")
    private String state;

}
