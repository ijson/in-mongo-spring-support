package com.ijson.mongo.dao.entity;

import com.google.common.collect.Lists;
import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.mongodb.morphia.annotations.*;
import com.ijson.mongo.generator.util.ObjectId;

import java.util.List;

@Data
@Entity(value = "User", noClassnameStored = true)
@ToString(callSuper = true)
public class UserEntity extends BaseEntity{


	@Property(Fields.id)
    private String id;

	@Property(Fields.username)
    private String username;

	@Property(Fields.password)
    private String password;


    @Property(Fields.lastModifiedBy)
    private String lastModifiedBy;

    @Property(Fields.deleted)
    private Boolean deleted;

    @Property(Fields.enable)
    private boolean enable;

    @Property(Fields.createdBy)
    private String createdBy;

    @Property(Fields.createTime)
    private long createTime;


    public interface Fields {
		String id = "_id";
		String username="username";
		String password="password";
        String createdBy = "createdBy";
        String createTime = "createTime";
        String enable = "enable";
        String deleted = "deleted";
        String lastModifiedBy ="lastModifiedBy";
    }
}

