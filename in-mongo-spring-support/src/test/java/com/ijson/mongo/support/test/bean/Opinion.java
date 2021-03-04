package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.generator.model.DocDescribe;
import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;

@Data
public class Opinion extends BaseEntity {

    @DocDescribe("手机号")
    private String mobile;
    @DocDescribe("QQ")
    private String qq;
    @DocDescribe("邮箱")
    private String email;
    @DocDescribe("意见或建议")
    private String opinion;


}
