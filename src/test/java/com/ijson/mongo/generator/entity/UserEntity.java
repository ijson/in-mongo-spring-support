package com.ijson.mongo.generator.entity;

import com.google.common.collect.Lists;
import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.mongodb.morphia.annotations.*;

import java.util.List;

@Data
@Entity(value = "User", noClassnameStored = true)
@ToString(callSuper = true)
public class UserEntity extends BaseEntity{


	@Property(Fields.id)
    private String id;

	@Property(Fields.name)
    private String name;

	@Property(Fields.age)
    private Integer age;


    public interface Fields {
		String id = "_id";
		String name="name";
		String age="age";
    }
}

