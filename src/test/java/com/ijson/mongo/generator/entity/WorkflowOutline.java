package com.ijson.mongo.generator.entity;

import com.google.common.collect.Lists;
import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.List;

@Data
@Entity(value = "WorkflowOutline", noClassnameStored = true)
@ToString(callSuper = true)
public class WorkflowOutline extends BaseEntity {
    @Id
    private String id;

    @Property(Fields.tenantId)
    private String tenantId = "";

    @Property(Fields.userId)
    private String userId = "";

    @Property(Fields.sourceWorkflowId)
    private String sourceWorkflowId = ""; //关联workflow soureid

    @Property(Fields.workflowId)
    private String workflowId = ""; //关联workflow soureid

    @Property(Fields.name)
    private String name = ""; //流程名

    @Property(Fields.enable)
    private boolean enabled; //是否激活

    @Property(Fields.description)
    private String description = "";

    @Property(Fields.entryType)
    private String entryType; //入口对象类型

    @Property(Fields.entryTypeName)
    private String entryTypeName; //入口对象名称

    @Property(Fields.rangeEmployeeIds)
    private List<Integer> rangeEmployeeIds = Lists.newArrayList();//可见部门范围

    @Property(Fields.rangeCircleIds)
    private List<Integer> rangeCircleIds = Lists.newArrayList();//可见人范围

    @Property(Fields.rangeGroupIds)
    private List<String> rangeGroupIds = Lists.newArrayList();//可见组范围


    public interface Fields {
        String id = "_id";
        String tenantId = "TId";
        String userId = "UId";
        String sourceWorkflowId = "SWId";
        String workflowId = "WId";
        String name = "N";
        String enable = "IsE";
        String description = "D";
        String rangeEmployeeIds = "REIds";
        String rangeCircleIds = "RCIds";
        String rangeGroupIds = "RGIds";
        String rangeRoleIds = "RRIds";
        String lastModifiedBy = "MId";
        String lastModifiedTime = "MT";
        String entryType = "ET";
        String entryTypeName = "ETN";
        String workflowJson = "JW";
        String extensionJson = "JE";
        String ruleJson = "RJ";
        String isDeleted = "IsD";
        String singleInstanceFlow = "SIF";
    }


}
