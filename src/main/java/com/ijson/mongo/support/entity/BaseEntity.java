package com.ijson.mongo.support.entity;

import lombok.Data;
import org.mongodb.morphia.annotations.Id;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/13 4:34 PM
 */
@Data
public class BaseEntity {

    @Id
    protected String id;

    private String shortUrl;

    private Boolean deleted = false;

    private Boolean enable = true;

    private String createdBy;

    private long createTime = System.currentTimeMillis();

    private String lastModifiedBy;

    private long lastModifiedTime = System.currentTimeMillis();

    public Boolean getDeleted() {
        return deleted != null && deleted;
    }

    public Boolean getEnable() {
        return enable != null && enable;
    }

    public interface Fields {
        String _id = "_id";
        String shortUrl = "shortUrl";
        String deleted = "deleted";
        String enable = "enable";
        String createdBy = "createdBy";
        String createTime = "createTime";
        String lastModifiedBy = "lastModifiedBy";
        String lastModifiedTime = "lastModifiedTime";
    }

}
