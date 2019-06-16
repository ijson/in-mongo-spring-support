package com.ijson.mongo.generator.entity;

import com.google.common.collect.Lists;
import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.mongodb.morphia.annotations.*;

import java.util.List;

@Data
@Entity(value = "WorkflowOutline", noClassnameStored = true)
@ToString(callSuper = true)
public class WorkflowOutlineEntity extends BaseEntity{


	@Property(Fields.id)
    private String id;

	@Property(Fields.tenantId)
    private String tenantId;

	@Property(Fields.userId)
    private String userId;

	@Property(Fields.sourceWorkflowId)
    private String sourceWorkflowId;

	@Property(Fields.workflowId)
    private String workflowId;

	@Property(Fields.name)
    private String name;

	@Property(Fields.enabled)
    private boolean enabled;

	@Property(Fields.description)
    private String description;

	@Property(Fields.entryType)
    private String entryType;

	@Property(Fields.entryTypeName)
    private String entryTypeName;

	@Property(Fields.rangeEmployeeIds)
    private List rangeEmployeeIds;

	@Property(Fields.rangeCircleIds)
    private List rangeCircleIds;

	@Property(Fields.rangeGroupIds)
    private List rangeGroupIds;


    public interface Fields {
		String id = "_id";
		String tenantId="tenantId";
		String userId="userId";
		String sourceWorkflowId="sourceWorkflowId";
		String workflowId="workflowId";
		String name="name";
		String enabled="enabled";
		String description="description";
		String entryType="entryType";
		String entryTypeName="entryTypeName";
		String rangeEmployeeIds="rangeEmployeeIds";
		String rangeCircleIds="rangeCircleIds";
		String rangeGroupIds="rangeGroupIds";
    }
}

