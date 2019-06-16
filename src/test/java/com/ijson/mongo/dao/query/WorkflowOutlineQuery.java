package com.ijson.mongo.dao.query;

import lombok.Data;
import java.util.List;

@Data
public class WorkflowOutlineQuery {

private String id;

private String tenantId;

private String userId;

private String sourceWorkflowId;

private String workflowId;

private String name;

private boolean enabled;

private String description;

private String entryType;

private String entryTypeName;

private List rangeEmployeeIds;

private List rangeCircleIds;

private List rangeGroupIds;

}

