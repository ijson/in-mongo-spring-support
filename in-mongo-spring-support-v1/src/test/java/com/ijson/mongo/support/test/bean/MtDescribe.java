package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.generator.model.DocDescribe;
import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;

@Data
public class MtDescribe extends BaseEntity {

    @DocDescribe("对象标识")
    private String api_name;
    @DocDescribe("对象中文标识")
    private String label;
    @DocDescribe("对象描述")
    private String description;
    @DocDescribe("字段类型:" +
            "select_one\n" +
            "select_many\n" +
            "text\n" +
            "long_text\n" +
            "object_reference\n" +
            "employee\n" +
            "date\n" +
            "time\n" +
            "data_time\n" +
            "number\n" +
            "true_or_false\n" +
            "auto_number\n" +
            "phone_number\n" +
            "currency\n" +
            "email")
    private String type;
    @DocDescribe("是否必填")
    private Boolean is_required;
    @DocDescribe("最大长度")
    private Integer max_length;
    @DocDescribe("自增编号,结构 {yyyy}{mm}{dd}-")
    private String prefix;
    @DocDescribe("邮箱,手机号等 正则验证")
    private String pattern;
    @DocDescribe("是否是主属性 ")
    private Boolean is_main_attribute;
    @DocDescribe("是否使用短连接 ")
    private Boolean use_short_url;





}
