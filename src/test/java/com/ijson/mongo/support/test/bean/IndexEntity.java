package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity(value = "index4", noClassnameStored = true)
@ToString(callSuper = true)
public class IndexEntity extends BaseEntity {

    @Id
    private String id;
    /**
     * 可以修改为Timestamp
     */
    private Date time;

    private String cname;

}
