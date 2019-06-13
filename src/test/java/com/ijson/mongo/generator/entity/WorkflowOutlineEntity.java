package com.ijson.mongo.generator.entity;

import com.google.common.collect.Lists;
import com.ijson.mongo.support.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.*;

import java.util.List;

@Setter
@Getter
@Entity(value = "WorkflowOutline", noClassnameStored = true)
@Indexes({
        @Index(name = "T_SId_IsD",
                fields = {
                        @Field(value = WorkflowOutlineEntity.Fields.tenantId),
                        @Field(WorkflowOutlineEntity.Fields.sourceWorkflowId),
                }),
        @Index(name = "T_N",
                fields = {
                        @Field(value = WorkflowOutlineEntity.Fields.tenantId),
                        @Field(WorkflowOutlineEntity.Fields.name),
                }),
        @Index(name = "T",
                fields = {
                        @Field(value = WorkflowOutlineEntity.Fields.tenantId)
                })
})
public class WorkflowOutlineEntity extends BaseEntity{
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

    @Property(Fields.rangeRoleIds)
    private List<String> rangeRoleIds = Lists.newArrayList();//可见使用角色范围

    @Property(Fields.createdBy)
    private String createdBy = "";

    @Property(Fields.createTime)
    private long createTime;

    @Property(Fields.lastModifiedBy)
    private String lastModifiedBy = "";

    @Property(Fields.lastModifiedTime)
    private long lastModifiedTime;

    @Property(Fields.workflowJson)
    private String workflowJson = "{}"; //当存储草稿时，workflow的json字符串

    @Property(Fields.extensionJson)
    private String extensionJson = "{}";//草稿时，扩展数据的字符串，包括pools\diagram等

    @Property(Fields.ruleJson)
    private String ruleJson;

    @Property(Fields.hasInstance)
    private boolean hasInstance;//是否有实例 在删除时会用到

    @Property(Fields.isDeleted)//标记删除
    private boolean isDeleted = false;

    @Property(Fields.count)
    private int count; //启动次数

    @Property(Fields.templateId)
    private String templateId;

    /**
     * 是外部流程1
     * 不是外部流程 0或空
     */
    @Property(Fields.externalFlow)
    private Integer externalFlow;
    /**
     * 是否为单实例流程 1 是
     * 0或空  不是
     */
    @Property(Fields.singleInstanceFlow)
    private Integer singleInstanceFlow;

    /**
     * //流程扩展类型  6.6添加 营销流程:1
     */
    @Property(Fields.supportFlowType)
    private Integer supportFlowType;

    public boolean isSingle() {
        return singleInstanceFlow != null && singleInstanceFlow == 1;
    }

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
        String createdBy = "CId";
        String createTime = "CT";
        String lastModifiedBy = "MId";
        String lastModifiedTime = "MT";
        String entryType = "ET";
        String entryTypeName = "ETN";
        String workflowJson = "JW";
        String extensionJson = "JE";
        String ruleJson = "RJ";
        String hasInstance = "HI";
        String isDeleted = "IsD";
        String count = "C";
        String templateId = "TI";
        String singleInstanceFlow = "SIF";
        String externalFlow = "EF";
        String sourceName = "SN";
        String outlineId = "OId";
        String supportFlowType = "SFT";
    }


}
