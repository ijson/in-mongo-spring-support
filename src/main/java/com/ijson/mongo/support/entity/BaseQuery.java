package com.ijson.mongo.support.entity;

import lombok.Data;

/**
 * desc:
 * author: cuiyongxu
 * create_time: 2021/6/25-8:28 上午
 **/
@Data
public class BaseQuery {

    protected String id;
    protected String shortUrl;
    protected Boolean deleted = false;
    protected Boolean enable = true;
    protected String createdBy;
    protected Long createTime;
    protected String lastModifiedBy;
    protected Long lastModifiedTime;

}
